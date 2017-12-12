package com.cookie.service.impl;

import com.cookie.dao.ProductInfoMapper;
import com.cookie.dto.CartDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.enums.ProductStatus;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.BasePageParam;
import com.cookie.pojo.ProductInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cookie.service.ProductInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by yr on 2017/12/11.
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfoDTO findOne(ProductInfo productInfo) {
        ProductInfoDTO productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);
        return productInfoDTO;
    }

    @Override
    public List<ProductInfoDTO> findUpAll() {
        return productInfoMapper.getUpAll();
    }

    @Override
    public PageInfo<ProductInfoDTO> findAll(BasePageParam basePageParam) {
        List<ProductInfoDTO> list= productInfoMapper.getAll();
        PageHelper.startPage(basePageParam.getPageNum(),basePageParam.getPageSize());
        PageInfo<ProductInfoDTO> productInfoDTOPageInfo=new PageInfo<ProductInfoDTO>(list);
        return productInfoDTOPageInfo;
    }

    @Override
    @Transactional
    public ProductInfoDTO save(ProductInfo productInfo) {
        productInfoMapper.addProductInfo(productInfo);
        ProductInfoDTO productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);
        return productInfoDTO;
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        ProductInfo productInfo = null;
        ProductInfoDTO productInfoDTO = null;
        Integer result=0;
        for(CartDTO cartDTO : cartDTOList){
            productInfo.setProductId(cartDTO.getProductId());
            productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);
            if(productInfoDTO == null){
                log.error("[商品加库] 查询商品失败 productInfo={}", productInfo);
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }

            result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(result);
            productInfoMapper.updateProductInfo(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        ProductInfo productInfo = null;
        ProductInfoDTO productInfoDTO = null;
        Integer result=0;
        for(CartDTO cartDTO : cartDTOList){
            productInfo.setProductId(cartDTO.getProductId());
            productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);
            if(productInfoDTO == null){
                log.error("[商品减库] 查询商品失败 productInfo={}", productInfo);
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }

            result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_INSUFFICIENT);
            }

            productInfo.setProductStock(result);
            productInfoMapper.updateProductInfo(productInfo);
        }
    }

    @Override
    @Transactional
    public ProductInfoDTO onSale(ProductInfo productInfo) {
        ProductInfoDTO productInfoDTO= productInfoMapper.getProductInfoByProductId(productInfo);
        if (productInfoDTO == null){
            log.error("[商品上架] 查询商品失败 productInfo={}", productInfo);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(productInfoDTO.getProductStatus().equals(ProductStatus.DOWN)){
            log.error("[商品上架] 商品状态错误 productInfo={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态为上架
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        productInfoMapper.updateProductInfo(productInfo);
        productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);

        return productInfoDTO;
    }

    @Override
    @Transactional
    public ProductInfoDTO offSale(ProductInfo productInfo) {
        ProductInfoDTO productInfoDTO= productInfoMapper.getProductInfoByProductId(productInfo);
        if (productInfoDTO == null){
            log.error("[商品下架] 查询商品失败 productInfo={}", productInfo);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(productInfoDTO.getProductStatus().equals(ProductStatus.DOWN)){
            log.error("[商品下架] 商品状态错误 productInfo={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态为下架
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        productInfoMapper.updateProductInfo(productInfo);
        productInfoDTO = productInfoMapper.getProductInfoByProductId(productInfo);

        return productInfoDTO;
    }
}
