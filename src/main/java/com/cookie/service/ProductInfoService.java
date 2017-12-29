package com.cookie.service;


import com.cookie.dto.CartDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.pojo.ProductInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    /**
     * 查询单个
     * @param productInfo
     * @return
     */
    ProductInfoDTO findOne(ProductInfo productInfo);

    /**
     * 查询所有在架视频列表
     * @return
     */
    List<ProductInfoDTO> findUpAll();

    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<ProductInfoDTO> findAll(Page page);

    /**
     * 新增
     * @param productInfo
     * @return
     */
    ProductInfoDTO save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     * @param productInfo
     * @return ProductInfoDTO
     */
    ProductInfoDTO onSale(ProductInfo productInfo);

    /**
     * 下架
     * @param productInfo
     * @return ProductInfoDTO
     */
    ProductInfoDTO  offSale(ProductInfo productInfo);

    /**
     * 删除
     * @param productInfo
     */
    void delete(ProductInfo productInfo);
}
