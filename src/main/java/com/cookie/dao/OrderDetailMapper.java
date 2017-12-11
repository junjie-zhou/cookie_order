package com.cookie.dao;

import com.cookie.dto.OrderDetailDTO;
import com.cookie.pojo.OrderDetail;
import com.cookie.pojo.OrderMaster;

import java.util.List;

/**
 * Created by asus on 2017/12/9.
 */
public interface OrderDetailMapper {


    OrderDetailDTO findByDetailId(OrderDetail orderDetail);

    List<OrderDetailDTO> findByOrderId(OrderDetail orderDetail);

    boolean insertOrderDetail(OrderDetail orderDetail);

    boolean updateByDetailId(OrderDetail orderDetail);

    boolean deleteByDetailId(OrderDetail orderDetail);

    boolean deleteByOrderId(OrderDetail orderDetail);

}
