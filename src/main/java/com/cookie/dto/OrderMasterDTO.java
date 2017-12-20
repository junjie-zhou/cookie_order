package com.cookie.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderMasterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * VARCHAR(32) 必填<br>
     * 
     */
    private String orderId;

    /**
     * VARCHAR(32) 必填<br>
     * 买家名字
     */
    private String buyerName;

    /**
     * VARCHAR(32) 必填<br>
     * 买家电话
     */
    private String buyerPhone;

    /**
     * VARCHAR(128) 必填<br>
     * 买家地址
     */
    private String buyerAddress;

    /**
     * VARCHAR(64) 必填<br>
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * DECIMAL(8,2) 必填<br>
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * TINYINT(3) 默认值[0] 必填<br>
     * 订单状态, 默认为新下单
     */
    private Integer orderStatus;

    /**
     * TINYINT(3) 默认值[0] 必填<br>
     * 支付状态, 默认未支付
     */
    private Integer payStatus;

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