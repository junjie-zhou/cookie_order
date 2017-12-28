package com.cookie.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by myseital  on 2017/11/23.
 */

@Data
public class SellerInfoDTO {


    private String id;

    private String username;

    private String password;

    private String openid;

    private Date createTime;

    private Date updateTime;
}
