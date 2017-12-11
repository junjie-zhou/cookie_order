package com.cookie.service;



import com.cookie.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds);

    ProductCategory save(ProductCategory productCategory);
}
