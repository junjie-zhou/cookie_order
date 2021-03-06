package com.cookie.dao.test;


import com.cookie.dao.ProductCategoryMapper;
import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCatalogDAOTest {

   @Autowired
   private ProductCategoryMapper productCategoryMapper;


   @Test
   public void testSelectByCategoryType(){
       ProductCategory productCategory=new ProductCategory();
       productCategory.setCategoryType(1);
       ProductCategoryDTO dto=productCategoryMapper.selectByCategoryType(productCategory);
      //Assert.assertEquals(dto,null);
       System.out.println(dto.toString());
   }

   @Test
   public void testInsertProductCategory(){
       int  result =productCategoryMapper.insertByMap(new ProductCategory("test1",1,new Date(),new Date()));

       Assert.assertTrue(result >0 );
   }

}
