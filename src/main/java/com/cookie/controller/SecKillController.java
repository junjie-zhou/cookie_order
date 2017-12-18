package com.cookie.controller;

import com.cookie.pojo.ProductInfo;
import com.cookie.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/skill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品信息
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId(productId);
        return secKillService.querySecKillProductInfo(productInfo);
    }


    /**
     * 秒杀，没有抢到获得"哎呦喂，xxx"，抢到了会返回剩余的库存量
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String kill(@PathVariable String productId) throws Exception {
        log.info("@skill request, productId:{}", productId);
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId(productId);
        secKillService.orderProductMockDiffUser(productInfo);
        return secKillService.querySecKillProductInfo(productInfo);
    }
}
