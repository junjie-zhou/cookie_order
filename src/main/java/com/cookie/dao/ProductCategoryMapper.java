package com.cookie.dao;


import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;

public interface ProductCategoryMapper {


   ProductCategoryDTO selectByCategoryType(Integer  categoryType);


   int insertByMap(ProductCategory productCategory);


   ProductCategory findByCategoryType(Integer categoryType);


   boolean insertByObject(ProductCategory productCategory);


   boolean deleteByCategoryType(Integer categoryType);


}
