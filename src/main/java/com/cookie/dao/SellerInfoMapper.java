package com.cookie.dao;

import com.cookie.dto.SellerInfoDTO;
import com.cookie.pojo.SellerInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by asus on 2017/12/10.
 */
@CacheConfig(cacheNames = "SellerInfoDTO")
public interface SellerInfoMapper {

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    SellerInfoDTO getSellerInfo(SellerInfo sellerInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean addSellerInfo(SellerInfo sellerInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean updateSellerInfo(SellerInfo sellerInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean deleteSellerInfo (SellerInfo sellerInfo);

}
