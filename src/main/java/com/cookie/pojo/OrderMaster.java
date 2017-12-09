package com.cookie.pojo;

import com.cookie.enums.OrderStatus;
import com.cookie.enums.PayStatus;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class OrderMaster {


    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态 默认为0新下单 */
    private Integer orderStatus = OrderStatus.NEW.getCode();

    /** 支付状态 默认为0未支付 */
    private Integer payStatus = PayStatus.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    public OrderMaster() {
    }

    public OrderMaster(String orderId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid,
                        BigDecimal orderAmount, Integer orderStatus, Integer payStatus, Date createTime, Date updateTime) {
        this.orderId = orderId;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
