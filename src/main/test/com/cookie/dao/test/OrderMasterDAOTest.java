package com.cookie.dao.test;

import com.cookie.dao.OrderMasterMapper;
import com.cookie.dto.OrderMasterDTO;
import com.cookie.pojo.OrderMaster;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;

/**
 * Created by zjj on 2017/12/10.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void addOrderMaster(){
        OrderMaster pojo=new OrderMaster("CK4378343","测试","测试","测试","测测",new BigDecimal(687.56),1,1);

        boolean result =orderMasterMapper.addOrderMaster(pojo);
        Assert.assertEquals(true,result);
    }


    @Test
    public void getOrderMaster(){
        OrderMasterDTO dto=orderMasterMapper.getOrderMasterByOrderId(new OrderMaster("CK4378343"));
        Assert.assertNotNull(dto);
        System.out.println(dto.toString());
    }

    @Test
    public void updateOrderMaster(){
        OrderMaster pojo=new OrderMaster("CK4378343","土豆","13446548796","测试","测测",new BigDecimal(1000),1,1);
       boolean result = orderMasterMapper.updateOrderMaster(pojo);
       Assert.assertTrue(result);
    }

}
