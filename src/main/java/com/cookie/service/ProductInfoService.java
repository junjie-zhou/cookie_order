package com.cookie.service;


import com.cookie.dto.CartDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.pojo.BasePageParam;
import com.cookie.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfoDTO findOne(ProductInfo productInfo);

    /**
     * 查询所有在架视频列表
     * @return
     */
    List<ProductInfoDTO> findUpAll();

    PageInfo<ProductInfoDTO> findAll(BasePageParam basePageParam);

    ProductInfoDTO save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfoDTO onSale(ProductInfo productInfo);

    //下架
    ProductInfoDTO  offSale(ProductInfo productInfo);
}
