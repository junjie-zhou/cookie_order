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
        sb.append(", detailId=").append(detailId);
        sb.append(", orderId=").append(orderId);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productQuantity=").append(productQuantity);
        sb.append(", productIcon=").append(productIcon);
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
        OrderDetailDTO other = (OrderDetailDTO) that;
        return (this.getDetailId() == null ? other.getDetailId() == null : this.getDetailId().equals(other.getDetailId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductQuantity() == null ? other.getProductQuantity() == null : this.getProductQuantity().equals(other.getProductQuantity()))
            && (this.getProductIcon() == null ? other.getProductIcon() == null : this.getProductIcon().equals(other.getProductIcon()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDetailId() == null) ? 0 : getDetailId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductQuantity() == null) ? 0 : getProductQuantity().hashCode());
        result = prime * result + ((getProductIcon() == null) ? 0 : getProductIcon().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}