package com.cookie.service.impl;

import com.cookie.dto.OrderDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.OrderMaster;
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
    public OrderDTO findOrderOne(OrderMaster orderMaste) {
        return checkOrderOwner(orderMaste);
    }

    @Override
    public OrderDTO cancelOrder(OrderMaster orderMast) {
        OrderDTO orderDTO = checkOrderOwner(orderMast);
        if (orderDTO == null) {
            log.error("[取消订单] 订单不存在,openid={}", orderMast.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderServer.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(OrderMaster orderMaste) {
        OrderDTO orderDTO = orderServer.findOne(orderMaste);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equals(orderMaste.getBuyerOpenid())) {
            log.error("[查询订单] 订单openid不一致,openid={}, orderDTO={}", orderMaste.getBuyerOpenid(), orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
