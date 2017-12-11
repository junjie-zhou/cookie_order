package com.cookie.service.impl;

import com.cookie.dto.OrderDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.service.BuyerService;
import com.cookie.service.OrderServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderServer orderServer;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("[取消订单] 订单不存在,openid={}", openid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderServer.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderServer.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("[查询订单] 订单openid不一致,openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
