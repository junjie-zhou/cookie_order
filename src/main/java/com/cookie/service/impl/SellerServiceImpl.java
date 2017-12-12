package com.cookie.service.impl;

import com.cookie.dao.SellerInfoMapper;
import com.cookie.dto.SellerInfoDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.exception.SellException;
import com.cookie.pojo.SellerInfo;
import com.cookie.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yr on 2017/12/11.
 */
@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Override
    public SellerInfoDTO findSellerInfoByOpenid(SellerInfo sellerInfo) {
        SellerInfoDTO sellerInfoDTO = sellerInfoMapper.getSellerInfo(sellerInfo);
        if(sellerInfoDTO == null){
            log.error("[微信公众账号方面错误] 微信公众账号openid不存在,openid={}", sellerInfo.getOpenid());
            throw new SellException(ResultEnum.WECHAT_MP_ERROR);
        }
        return sellerInfoDTO;
    }
}
