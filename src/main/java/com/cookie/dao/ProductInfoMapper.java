package com.cookie.dao;

import com.cookie.dto.ProductInfoDTO;
import com.cookie.pojo.ProductInfo;

import java.util.List;

/**
 * Created by asus on 2017/12/10.
 */
public interface ProductInfoMapper {

    List<ProductInfoDTO> getAll();

    List<ProductInfoDTO> getUpAll();

    ProductInfoDTO getProductInfoByProductId(ProductInfo productInfo);

    List<ProductInfoDTO> getProductInfoListByCategoryType(ProductInfo productInfo);

    boolean addProductInfo(ProductInfo productInfo);

    boolean updateProductInfo(ProductInfo productInfo);

    boolean deleteProductInfoByProductId(ProductInfo productInfo);

}
