package com.cookie.dao;

import com.cookie.dto.OrderMasterDTO;
import com.cookie.pojo.OrderMaster;

<<<<<<< Updated upstream
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
=======
/**
 * Created by asus on 2017/12/10.
 */
public interface OrderMasterMapper {

    OrderMasterDTO findByOrderId(OrderMaster orderMaster);

    OrderMasterDTO findByBuyerOpenId(OrderMaster orderMaster);

    boolean insert(OrderMaster orderMaster);

    boolean updateByOrderId(OrderMaster orderMaster);

    boolean delectByOrderId(OrderMaster orderMaster);

    boolean deleteByBuyerOpenId(OrderMaster orderMaster);

>>>>>>> Stashed changes
}
