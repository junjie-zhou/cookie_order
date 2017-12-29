package com.cookie.service.impl;

import com.cookie.dao.ProductCategoryMapper;
import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;
import com.cookie.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by yr on 2017/12/11.
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryDTO findOne(ProductCategory productCategory) {
        return productCategoryMapper.findByCategoryType(productCategory);

    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        return productCategoryMapper.getAll();
    }

    @Override
    public List<ProductCategoryDTO> findByCategoryTypeIn(List<ProductCategory> productCategorys) {
        List<ProductCategoryDTO> list=null;
        for(ProductCategory productCategory : productCategorys){
            ProductCategoryDTO productCategoryDTO = productCategoryMapper.findByCategoryType(productCategory);
            list.add(productCategoryDTO);
        }
        return list;
    }

    @Override
    @Transactional
    public ProductCategoryDTO save(ProductCategory productCategory) {
        System.out.println(productCategory.getCreateTime());
        if(!StringUtils.isEmpty(productCategory.getCreateTime())){
            productCategory.setUpdateTime(new Date());
            productCategoryMapper.updateByCategoryId(productCategory);
        }else {
            productCategory.setCreateTime(new Date());
            productCategory.setUpdateTime(new Date());
            productCategoryMapper.insertByObject(productCategory);
        }
        ProductCategoryDTO productCategoryDTO = productCategoryMapper.findByCategoryType(productCategory);
        return productCategoryDTO;
    }
}
