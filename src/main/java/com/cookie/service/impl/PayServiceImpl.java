package com.cookie.service.impl;

import com.cookie.dto.OrderDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.OrderMaster;
import com.cookie.service.OrderServer;
import com.cookie.service.PayService;
import com.cookie.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by myseital  on 2017/11/21.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private OrderServer orderServer;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付, request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付, response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1.验证签名
        // 2.支付的状态
        // 3.支付金额
        // 4.支付人（下单人==支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知, payResponse={}", JsonUtil.toJson(payResponse));

        //查询订单
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId(payResponse.getOrderId());
        OrderDTO orderDTO = orderServer.findOne(orderMaster);
        if (orderDTO == null) {
            log.error("[微信支付] 异步通知，订单不存在,orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致 (0.10 跟 0.1的比较)
        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("[微信支付] 异步通知，订单金额不一致,orderId={}，微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderServer.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] request={}", refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] response={}", refundResponse);

        return refundResponse;
    }
}
