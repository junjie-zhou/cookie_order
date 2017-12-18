package com.cookie.service;


import com.cookie.dto.OrderDTO;
import com.cookie.pojo.OrderMaster;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * Created by myseital  on 2017/11/20.
 */
public interface OrderServer {

    /**
     * 创建订单
     *
     * @param orderDTO 创建的orderDTO对象
     * @return 创建好的OrderDTO
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     *
     * @param orderMaste 订单id
     * @return 查询到的OrderDTO
     */
    OrderDTO findOne(OrderMaster orderMaste);


    /**
     * 取消订单
     *
     * @param orderDTO 被取消的orderDTO对象
     * @return 取消之后的OrderDTO
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完成订单
     *
     * @param orderDTO 被完成的订单OrderDTo对象
     * @return 完成好的订单OrderDTo对象
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     *
     * @param orderDTO 被支付的订单OrderDTO对象
     * @return 支付完的订单OrderDTO对象
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     *
     * @return 分页对象
     */
    PageInfo<OrderDTO> findList(OrderMaster reuest,Page page);

    PageInfo<OrderDTO> findList(Page page);
}