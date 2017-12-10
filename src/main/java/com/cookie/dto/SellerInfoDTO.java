package com.cookie.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by myseital  on 2017/11/23.
 */
@Entity
@Data
public class SellerInfoDTO {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

    private Date createTime;

    private Date updateTime;
}
