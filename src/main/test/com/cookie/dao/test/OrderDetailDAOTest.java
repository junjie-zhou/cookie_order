package com.cookie.dao.test;

import com.cookie.CookieApplication;
import com.cookie.dao.OrderDetailMapper;
import com.cookie.dto.OrderDetailDTO;
import com.cookie.pojo.OrderDetail;
import com.cookie.pojo.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDAOTest {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void testInsertOrderDetail(){
        OrderDetail orderDetail=new OrderDetail("1","1","1","shouji",new BigDecimal(12),1,"fdgfyrtehfdh",new Date(),new Date());
        boolean b = orderDetailMapper.insertOrderDetail(orderDetail);
        System.out.println(b);
    }

    @Test
    public void testUpdateByDetailId(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setProductQuantity(2);
        orderDetail.setUpdateTime(new Date());
        boolean b = orderDetailMapper.updateByDetailId(orderDetail);
        System.out.println(b);
    }

    @Test
    public void testFindByOrderId(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1");
        OrderDetailDTO byDetailId = orderDetailMapper.findByDetailId(orderDetail);
        System.out.println(byDetailId.toString());

    }

    @Test
    public void testFindByDetailId(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1");
        List<OrderDetailDTO> byOrderId = orderDetailMapper.findByOrderId(orderMaster);
        for(OrderDetailDTO orderDetailDTO:byOrderId){
            System.out.println(orderDetailDTO.toString());
        }

    }

    @Test
    public void testDeleteByDetailId(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("2");
        boolean b = orderDetailMapper.deleteByDetailId(orderDetail);
        System.out.println(b);


    }

    @Test
    public void testDeleteByOrderId(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1");
        boolean b = orderDetailMapper.deleteByOrderId(orderMaster);
        System.out.println(b);
    }



}
