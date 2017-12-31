package com.cookie.dao;


import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "ProductCategoryDTO")
public interface ProductCategoryMapper {

   @Cacheable(keyGenerator = "wiselyKeyGenerator")
   List<ProductCategoryDTO> getAll();

   @Cacheable(keyGenerator="wiselyKeyGenerator")
   ProductCategoryDTO selectByCategoryType(ProductCategory  productCategory);

   @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
   int insertByMap(ProductCategory productCategory);

   @Cacheable(keyGenerator="wiselyKeyGenerator")
   ProductCategoryDTO findByCategoryType(ProductCategory  productCategory);

   @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
   boolean updateByCategoryId(ProductCategory  productCategory);

   @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
   boolean insertByObject(ProductCategory productCategory);

   @CacheEvict(keyGenerator="wiselyKeyGenerator",allEntries=true)
   boolean deleteByCategoryType(ProductCategory  productCategory);


}
