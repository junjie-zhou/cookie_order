package com.cookie.dao.test;

import com.cookie.CookieApplication;
import com.cookie.dao.ProductCategoryMapper;
import com.cookie.dao.ProductInfoMapper;
import com.cookie.dto.ProductCategoryDTO;
import com.cookie.dto.ProductInfoDTO;
import com.cookie.pojo.ProductCategory;
import com.cookie.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    public void testAddProductInfo(){
        ProductInfo productInfo=new ProductInfo("10SDCX45","测试",200,new BigDecimal(1600),"测试","测试",0,5424852,
                new Date(),new Date());
        boolean b = productInfoMapper.addProductInfo(productInfo);
        Assert.assertEquals(true,b);
    }

    @Test
    public void testGetProductInfoListByCategoryType(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setCategoryType(5424852);
        List<ProductInfoDTO> productInfoListByCategoryType = productInfoMapper.getProductInfoListByCategoryType(productInfo);
        for(ProductInfoDTO p : productInfoListByCategoryType){
            System.out.println(p.toString());
        }

    }

    @Test
    public void testGetProductInfoByProductId(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("10SDCX45");
        ProductInfoDTO productInfoByProductId = productInfoMapper.getProductInfoByProductId(productInfo);
        Assert.assertNotNull(productInfoByProductId);
        System.out.println(productInfoByProductId.toString());
    }

    @Test
    public void testUpdateProductInfo(){
        ProductInfo productInfo=new ProductInfo("10SDCX45","测试更新",150,new BigDecimal(1700),"测试更新","测试更新",1,5424825,
                new Date(),new Date());
        boolean b = productInfoMapper.updateProductInfo(productInfo);
        Assert.assertEquals(true,b);
    }

    @Test
    public void testDeleteProductInfoByProductId(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("10SDCX45");
        boolean b = productInfoMapper.deleteProductInfoByProductId(productInfo);
        Assert.assertEquals(true,b);
    }

}
