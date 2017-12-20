package com.cookie.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * VARCHAR(32) 必填<br>
     * 
     */
    private String detailId;

    /**
     * VARCHAR(32) 必填<br>
     * 
     */
    private String orderId;

    /**
     * VARCHAR(32) 必填<br>
     * 
     */
    private String productId;

    /**
     * VARCHAR(64) 必填<br>
     * 商品名称
     */
    private String productName;

    /**
     * DECIMAL(8,2) 必填<br>
     * 当前价格,单位分
     */
    private BigDecimal productPrice;

    /**
     * INTEGER(10) 必填<br>
     * 数量
     */
    private Integer productQuantity;

    /**
     * VARCHAR(512)<br>
     * 小图
     */
    private String productIcon;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 创建时间
     */
    private String createTime;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 修改时间
     */
    private String updateTime;

}