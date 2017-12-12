package com.cookie.service;

import com.cookie.pojo.ProductInfo;

public interface SecKillService {

    String querySecKillProductInfo(ProductInfo productInfo);

    void orderProductMockDiffUser(ProductInfo productInfo);
}
