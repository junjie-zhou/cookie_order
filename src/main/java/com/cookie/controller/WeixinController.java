package com.cookie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by myseital  on 2017/11/21.
 */
@RestController
@RequestMapping(value = "/weixin")
@Slf4j
public class WeixinController {

    @GetMapping(value = "/auth")
    public void auth(@RequestParam("code") String code) {

        //1. 设置域名
        //微信 调试工具  服务器映射到本机localhost natapp.cn

        //2.用户同意授权，获取code
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE
        //appid=APPID 公众号的唯一标识
        //请求路径 redirect_uri=REDIRECT_URI  Http://..../weixin/auth
        //默认拥有scope参数中的snsapi_base和snsapi_userinfo

        //用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE
        // 微信会redirect 进入该方法 并附带两个参数 code和state
        log.info("进入auth方法!");
        log.info("code={}", code);

        // url获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // response 中的数据
        //{ "access_token":"ACCESS_TOKEN", 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
        //  "expires_in":7200, access_token接口调用凭证超时时间，单位（秒）
        //  "refresh_token":"REFRESH_TOKEN", 用户刷新access_token
        //  "openid":"OPENID",用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
        //  "scope":"SCOPE" 用户授权的作用域，使用逗号（,）分隔
        // }
        log.info("response={}", response);

        // 拉取用户信息(需scope为 snsapi_userinfo)
    }
}
