package com.cookie.service;


import com.cookie.dto.OrderDTO;

public interface BuyerService {

    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
