package com.cookie.service;


import com.cookie.pojo.SellerInfo;

/**
 * Created by myseital  on 2017/11/23.
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
