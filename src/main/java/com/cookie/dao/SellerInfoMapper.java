package com.cookie.dao;

import com.cookie.dto.SellerInfoDTO;
import com.cookie.pojo.SellerInfo;

/**
 * Created by asus on 2017/12/10.
 */
public interface SellerInfoMapper {

    SellerInfoDTO getSellerInfo(SellerInfo sellerInfo);

    boolean addSellerInfo(SellerInfo sellerInfo);

    boolean updateSellerInfo(SellerInfo sellerInfo);

    boolean deleteSellerInfo (SellerInfo sellerInfo);

}
