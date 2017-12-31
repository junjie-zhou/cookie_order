package com.cookie.dao;

import com.cookie.dto.OrderMasterDTO;
import com.cookie.pojo.OrderMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zjj on 2017/12/10.
 */
@CacheConfig(cacheNames = "OrderMasterDTO")
public interface OrderMasterMapper {

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean addOrderMaster(OrderMaster orderMaster);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean updateOrderMaster(OrderMaster orderMaster);

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    OrderMasterDTO getOrderMasterByOrderId(OrderMaster orderMaster);

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    List<OrderMasterDTO> getOrderMasterAllList();

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    List<OrderMasterDTO> getOrderMasterList(OrderMaster orderMaster);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean deleteOrderMasterByParam(OrderMaster orderMaster);
}
