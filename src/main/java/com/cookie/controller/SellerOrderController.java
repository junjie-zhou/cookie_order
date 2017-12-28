package com.cookie.controller;

import com.cookie.dto.OrderDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.OrderMaster;
import com.cookie.service.OrderServer;
import com.cookie.service.WebSocket;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {


    @Autowired
    private OrderServer orderServer;

    @Autowired
    private WebSocket webSocket;

    /**
     * 订单列表
     *
     * @param page 从第几页开始查询
     * @param size 一页展示几条记录
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page request=new Page(page-1,size);
        PageInfo<OrderDTO> orderDTOPage = orderServer.findList(request);
        Map<String, Object> map = new HashMap();
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("order/list", map);
    }

    @ResponseBody
    @GetMapping("/list1")
    public List<OrderDTO> list1(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page request=new Page(page-1,size);
        PageInfo<OrderDTO> orderDTOPage = orderServer.findList(request);

        return orderDTOPage.getList();
    }

    @GetMapping("/success")
    public ModelAndView success() {
        Map<String, Object> map = new HashMap();
        map.put("msg", ResultEnum.SUCCESS.getMessage());

        return new ModelAndView("common/success1");
    }


    /**
     * 卖家端取消订单
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam("orderId") String orderId, Integer detail) {
        Map<String, Object> map = new HashMap();
        try {
            OrderMaster orderMaster=new OrderMaster();
            orderMaster.setOrderId(orderId);
            OrderDTO orderDTO = orderServer.findOne(orderMaster);
            if (orderDTO == null) {
                log.error("[卖家端取消订单] 查询不到订单");
                map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
                map.put("url", "/sell/seller/order/list");

                return new ModelAndView("common/error", map);
            }
            orderServer.cancel(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端取消订单] 发生异常：{}", e);
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        if (detail == 1) {
            map.put("url", "/sell/seller/order/detail?orderId=" + orderId);
        } else {
            map.put("url", "/sell/seller/order/list?page=" + page);
        }

        return new ModelAndView("common/success", map);
    }

    /**
     * 卖家端查看订单详情
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam("orderId") String orderId) {
        Map<String, Object> map = new HashMap();
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId(orderId);
        OrderDTO orderDTO = orderServer.findOne(orderMaster);
        if (orderDTO == null) {
            log.error("[卖家端查看订单详情] 查询不到订单");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            System.out.println("[卖家端查看订单详情] 查询不到订单");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        map.put("page",page);

        return new ModelAndView("order/detail", map);
    }


    /**
     * 卖家端完结订单
     *
     * @param orderId 订单ID
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam("orderId") String orderId, Integer detail) {
        Map<String, Object> map = new HashMap();
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId(orderId);
        OrderDTO orderDTO = orderServer.findOne(orderMaster);
        if (orderDTO == null) {
            log.error("[卖家端完结订单] 查询不到订单");
            map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }
        orderServer.finish(orderDTO);

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        if (detail == 1) {
            map.put("url", "/sell/seller/order/detail?orderId=" + orderId);
        } else {
            map.put("url", "/sell/seller/order/list?page=" + page);
        }

        return new ModelAndView("common/success", map);
    }
}
