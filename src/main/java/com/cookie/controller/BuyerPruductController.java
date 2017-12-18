package com.cookie.controller;

import com.cookie.dto.ProductCategoryDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.dto.ProductInfoVO;
import com.cookie.dto.ProductVO;
import com.cookie.pojo.ProductCategory;
import com.cookie.service.ProductCategoryService;
import com.cookie.service.ProductInfoService;
import com.cookie.utils.Result;
import com.cookie.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerPruductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public Result list(){
        //1.查询所有的上架商品
        List<ProductInfoDTO> productInfoList = productInfoService.findUpAll();

        //2. 查询类目(一次性查询)
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList=new ArrayList<ProductCategory>();
        for(Integer categoryType:categoryTypeList){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryType(categoryType);
            productCategoryList.add(productCategory);
        }
        List<ProductCategoryDTO> productCategoryDTOList = productCategoryService.findByCategoryTypeIn(productCategoryList);

        //3. 数据拼接
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            //productVO.setCategoryType(productCategory.getCategoryType());
            //productVO.setCategoryName(productCategory.getCategoryName());
            BeanUtils.copyProperties(productCategory, productVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfoDTO productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultUtil.success(productVOList);
    }
}
