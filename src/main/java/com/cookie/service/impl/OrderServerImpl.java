package com.cookie.service.impl;


import com.cookie.common.converter.OrderMaster2OrderDTOConverter;
import com.cookie.dao.OrderDetailMapper;
import com.cookie.dao.OrderMasterMapper;
import com.cookie.dto.*;
import com.cookie.enums.OrderStatus;
import com.cookie.enums.PayStatus;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.OrderDetail;
import com.cookie.pojo.OrderMaster;
import com.cookie.pojo.ProductInfo;
import com.cookie.service.*;
import com.cookie.utils.KeyUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.cookie.service.ProductInfoService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by myseital  on 2017/11/20.
 */
@Service
@Slf4j
public class OrderServerImpl implements OrderServer {

    @Autowired
    private OrderDetailMapper orderDetailRepository;

    @Autowired
    private OrderMasterMapper orderMasterRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //List<CartDTO> cartDTOList = new ArrayList<>();

        //1. 查询商品(数量，价格)
        for (OrderDetailDTO orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo=new ProductInfo();
            productInfo.setProductId(orderDetail.getProductId());
            ProductInfoDTO productInfoDTO = productInfoService.findOne(productInfo);
            if (productInfoDTO == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfoDTO.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // 订单详情存入数据库
            BeanUtils.copyProperties(productInfoDTO, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            OrderDetail dto=new OrderDetail();
            BeanUtils.copyProperties(orderDetail, dto);
            orderDetailRepository.insertOrderDetail(dto);
        }

        //3. 写入订单数据库（orderMaster，orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //BeanUtils 先复制,后赋值初始化的值和有用的值
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterRepository.addOrderMaster(orderMaster);

        //4. 减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        // 发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(OrderMaster orderMaste) {
        OrderMasterDTO orderMaster = orderMasterRepository.getOrderMasterByOrderId(new OrderMaster(orderMaste.getOrderId()));
        if (orderMaster == null) {
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetailDTO> orderDetailList = orderDetailRepository.findByOrderId(new OrderDetail(orderMaste.getOrderId()));
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw  new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }


    public PageInfo<OrderDTO> findList(OrderMaster orderMaster, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        PageInfo<OrderMasterDTO> orderMasterPage =new PageInfo<>(orderMasterRepository.getOrderMasterList(orderMaster));
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getList());
        webSocket.sendMessage("测试数据");
        return new PageInfo<>(orderDTOList);
    }

    @Override
    public PageInfo<OrderDTO> findList(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        PageInfo<OrderMasterDTO> orderMasterPage =new PageInfo<>(orderMasterRepository.getOrderMasterAllList());
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getList());
        webSocket.sendMessage("测试数据");
        return new PageInfo<>(orderDTOList);
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[取消订单] 订单状态不正确， orderId={}， orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        boolean updateResult = orderMasterRepository.addOrderMaster(orderMaster);
        if (!updateResult) {
            log.error("[取消订单] 订单更新失败， orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_UPDATE_FAIL);
        }

        //3. 返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单] 订单中无商品详情， orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //4. 如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        //1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[完成订单] 订单状态不正确， orderId={}， orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatus.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        boolean updateResult = orderMasterRepository.addOrderMaster(orderMaster);
        if (!updateResult) {
            log.error("[完成订单] 订单更新失败， orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_UPDATE_FAIL);
        }

        // 推送微信模板信息
        pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        //1. 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[订单支付完成] 订单状态不正确， orderId={}， orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2. 判断订单支付状态
        if (!orderDTO.getPayStatus().equals(PayStatus.WAIT.getCode())) {
            log.error("[订单支付完成] 订单支付状态不正确， orderId={}， payStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }


        //3. 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        boolean updateResult = orderMasterRepository.addOrderMaster(orderMaster);
        if ( !updateResult) {
            log.error("[订单支付完成] 订单更新失败， orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_STATUS_UPDATE_FAIL);
        }

        return orderDTO;
    }


   /* public PageInfo<OrderDTO> findList(OrderMaster request) {
        PageInfo<OrderMasterDTO> orderMasterPage = new PageInfo<>(orderMasterRepository.getOrderMasterList(request));
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getList());

        return new PageInfo<>(orderDTOList);
    }*/
}
