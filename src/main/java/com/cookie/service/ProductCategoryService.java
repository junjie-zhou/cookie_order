package com.cookie.service;



import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryDTO findOne(ProductCategory productCategory);

    List<ProductCategoryDTO> findAll();

    List<ProductCategoryDTO> findByCategoryTypeIn(List<ProductCategory> productCategory);

    ProductCategoryDTO save(ProductCategory productCategory);

    void delete(ProductCategory productCategory);
}
