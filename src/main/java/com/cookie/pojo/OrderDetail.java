package com.cookie.pojo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class OrderDetail {

    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

    public OrderDetail() {
    }

    public OrderDetail(String detailId) {
        this.detailId = detailId;
    }

    public OrderDetail(String detailId, String orderId, String productId, String productName, BigDecimal productPrice,
                       Integer productQuantity, String productIcon, Date createTime, Date updateTime) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productIcon = productIcon;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
