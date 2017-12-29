package com.cookie.dao;


import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryMapper {

   List<ProductCategoryDTO> getAll();


   ProductCategoryDTO selectByCategoryType(ProductCategory  productCategory);


   int insertByMap(ProductCategory productCategory);


   ProductCategoryDTO findByCategoryType(ProductCategory  productCategory);


   boolean updateByCategoryId(ProductCategory  productCategory);

   boolean insertByObject(ProductCategory productCategory);


   boolean deleteByCategoryType(ProductCategory  productCategory);


}
