package com.cookie.dao;

import com.cookie.dto.ProductInfoDTO;
import com.cookie.pojo.ProductInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by asus on 2017/12/10.
 */
@CacheConfig(cacheNames = "ProductInfoDTO")
public interface ProductInfoMapper {

    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    List<ProductInfoDTO> getAll();

    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    List<ProductInfoDTO> getUpAll();

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    ProductInfoDTO getProductInfoByProductId(ProductInfo productInfo);

    @Cacheable(keyGenerator="wiselyKeyGenerator")
    List<ProductInfoDTO> getProductInfoListByCategoryType(ProductInfo productInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean addProductInfo(ProductInfo productInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean updateProductInfo(ProductInfo productInfo);

    @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
    boolean deleteProductInfoByProductId(ProductInfo productInfo);

}
