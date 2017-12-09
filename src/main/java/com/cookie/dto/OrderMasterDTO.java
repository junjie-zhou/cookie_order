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
    private Byte orderStatus;

    /**
     * TINYINT(3) 默认值[0] 必填<br>
     * 支付状态, 默认未支付
     */
    private Byte payStatus;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 创建时间
     */
    private Date createTime;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 修改时间
     */
    private Date updateTime;
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", buyerName=").append(buyerName);
        sb.append(", buyerPhone=").append(buyerPhone);
        sb.append(", buyerAddress=").append(buyerAddress);
        sb.append(", buyerOpenid=").append(buyerOpenid);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderMasterDTO other = (OrderMasterDTO) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getBuyerName() == null ? other.getBuyerName() == null : this.getBuyerName().equals(other.getBuyerName()))
            && (this.getBuyerPhone() == null ? other.getBuyerPhone() == null : this.getBuyerPhone().equals(other.getBuyerPhone()))
            && (this.getBuyerAddress() == null ? other.getBuyerAddress() == null : this.getBuyerAddress().equals(other.getBuyerAddress()))
            && (this.getBuyerOpenid() == null ? other.getBuyerOpenid() == null : this.getBuyerOpenid().equals(other.getBuyerOpenid()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getBuyerName() == null) ? 0 : getBuyerName().hashCode());
        result = prime * result + ((getBuyerPhone() == null) ? 0 : getBuyerPhone().hashCode());
        result = prime * result + ((getBuyerAddress() == null) ? 0 : getBuyerAddress().hashCode());
        result = prime * result + ((getBuyerOpenid() == null) ? 0 : getBuyerOpenid().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}