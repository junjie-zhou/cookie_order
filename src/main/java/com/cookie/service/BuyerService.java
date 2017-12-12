package com.cookie.service;


import com.cookie.dto.OrderDTO;
import com.cookie.pojo.OrderMaster;

public interface BuyerService {

    /**
     * 查询一个订单
     * @param orderMaster
     * @return
     */
    OrderDTO findOrderOne(OrderMaster orderMaster);

    /**
     * 取消一个订单
     * @param orderMaster
     * @return
     */
    OrderDTO cancelOrder(OrderMaster orderMaster);
}
