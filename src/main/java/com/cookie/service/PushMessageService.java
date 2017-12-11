package com.cookie.service;


import com.cookie.dto.OrderDTO;

/**
 * 推送消息
 * Created by myseital  on 2017/11/24.
 */
public interface PushMessageService {

    /**
     * 订单状态变更信息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
