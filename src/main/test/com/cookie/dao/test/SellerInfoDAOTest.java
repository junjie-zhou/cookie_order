package com.cookie.dao.test;

import com.cookie.dao.SellerInfoMapper;
import com.cookie.dto.SellerInfoDTO;
import com.cookie.pojo.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDAOTest {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Test
    public void testAddSellerInfo(){
        SellerInfo sellerInfo=new SellerInfo("123456","ceshi","ceshi","ceshi",new Date(),new Date());
        boolean b = sellerInfoMapper.addSellerInfo(sellerInfo);
        Assert.assertEquals(true,b);
    }

    @Test
    public void testGetSellerInfo(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setId("123456");
        SellerInfoDTO s = sellerInfoMapper.getSellerInfo(sellerInfo);
        Assert.assertNotNull(s);
        System.out.println(s.toString());
    }

    @Test
    public void testUpdateSellerInfo(){
        SellerInfo sellerInfo=new SellerInfo("123456","ceshixiugai","ceshixiugai","ceshixiugai",new Date(),new Date());
        boolean b = sellerInfoMapper.updateSellerInfo(sellerInfo);
        Assert.assertEquals(true,b);
    }

    @Test
    public void testDeleteSellerInfo(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setId("123456");
        boolean b = sellerInfoMapper.deleteSellerInfo(sellerInfo);
        Assert.assertEquals(true,b);
    }

}
