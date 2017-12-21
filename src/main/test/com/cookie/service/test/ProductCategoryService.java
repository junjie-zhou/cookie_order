package com.cookie.service.test;


import com.cookie.dto.ProductCategoryDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryService {

    @Autowired
    private com.cookie.service.ProductCategoryService productCategoryService;



    @Test
    public void testList(){
        try {
            List<ProductCategoryDTO> list = productCategoryService.findAll();

            System.out.println(list.toString());
            Assert.assertTrue(list.size() > 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
