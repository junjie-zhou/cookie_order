package com.cookie.dao.test;

import com.cookie.dao.OrderMasterMapper;
<<<<<<< Updated upstream
import com.cookie.dto.OrderMasterDTO;
import com.cookie.pojo.OrderMaster;
=======
import com.cookie.dao.ProductCategoryMapper;
import com.cookie.dto.ProductCategoryDTO;
import com.cookie.pojo.OrderMaster;
import com.cookie.pojo.ProductCategory;
>>>>>>> Stashed changes
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

<<<<<<< Updated upstream
import java.math.BigDecimal;

/**
 * Created by zjj on 2017/12/10.
 */
=======

import java.math.BigDecimal;
import java.util.Date;

>>>>>>> Stashed changes

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
<<<<<<< Updated upstream
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
=======
    public void testInsert(){
        OrderMaster orderMaster=new OrderMaster("100001","zhangsan","18888888888","北京朝阳区","18888888888",
                new BigDecimal("1200.6"),0,0,new Date(),new Date());
        boolean insert = orderMasterMapper.insert(orderMaster);
        System.out.println(insert);
    }

    @Test
    public void testUpdateByOrderId(){
        OrderMaster orderMaster=new OrderMaster("100001","zhangsan","18888888888","北京朝阳区","18888888888",
                new BigDecimal("1200.6"),0,1,new Date(),new Date());
        boolean insert = orderMasterMapper.updateByOrderId(orderMaster);
        System.out.println(insert);
    }


>>>>>>> Stashed changes
}
