package com.cookie.dao;

import com.cookie.dto.OrderMasterDTO;
import com.cookie.pojo.OrderMaster;

import java.util.List;

/**
 * Created by zjj on 2017/12/10.
 */
public interface OrderMasterMapper {

    boolean  addOrderMaster(OrderMaster orderMaster);

    boolean  updateOrderMaster(OrderMaster orderMaster);

    OrderMasterDTO getOrderMasterByOrderId(OrderMaster orderMaster);

    List<OrderMasterDTO> getOrderMasterList(OrderMaster orderMaster);

    boolean deleteOrderMasterByParam(OrderMaster orderMaster);
}
