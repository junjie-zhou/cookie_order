package com.cookie.dao;

import com.cookie.dto.OrderDetailDTO;
import com.cookie.pojo.OrderDetail;
import com.cookie.pojo.OrderMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by asus on 2017/12/9.
 */
@CacheConfig(cacheNames = "OrderDetailDTO")
public interface OrderDetailMapper {

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    OrderDetailDTO findByDetailId(OrderDetail orderDetail);

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    List<OrderDetailDTO> findByOrderId(OrderDetail orderDetail);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean insertOrderDetail(OrderDetail orderDetail);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean updateByDetailId(OrderDetail orderDetail);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean deleteByDetailId(OrderDetail orderDetail);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean deleteByOrderId(OrderDetail orderDetail);

}
