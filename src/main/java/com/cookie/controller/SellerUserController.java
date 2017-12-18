package com.cookie.controller;

import com.cookie.common.config.ProjectUrlConfig;
import com.cookie.common.constant.CookieConstant;
import com.cookie.common.constant.RedisConstant;
import com.cookie.dto.SellerInfoDTO;
import com.cookie.enums.ResultEnum;
import com.cookie.pojo.SellerInfo;
import com.cookie.service.SellerService;
import com.cookie.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Created by myseital  on 2017/11/23.
 */
@Controller
@RequestMapping("/seller/user")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, Map<String, Object> map, HttpServletResponse response) {


        // 1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenid(openid);
        SellerInfoDTO sellerInfoDTO = sellerService.findSellerInfoByOpenid(sellerInfo);
        if (sellerInfoDTO == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        // 2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        // 3.设置token至cookies
        //Cookie cookie = new Cookie("token", token);
        //cookie.setPath("/");
        //cookie.setMaxAge(7200);

        CookieUtil.set(response, CookieConstant.TOKEN, token , CookieConstant.EXPIRE);

        return new ModelAndView("redirect:/seller/order/list");
        //return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) {

        // 1.Cookie查询token
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            // 2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            // 3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null , 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);
    }
}
