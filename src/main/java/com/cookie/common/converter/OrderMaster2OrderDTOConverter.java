package com.cookie.common.converter;


import com.cookie.dto.OrderDTO;
import com.cookie.dto.OrderMasterDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMasterDTO orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMasterDTO> orderMasterList) {

        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }

}
