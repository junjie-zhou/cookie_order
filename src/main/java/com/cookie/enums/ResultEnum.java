package com.cookie.enums;

import lombok.Getter;

/**
 * Created by myseital  on 2017/11/20.
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_INSUFFICIENT(11, "商品库存不足"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态错误"),

    ORDER_STATUS_UPDATE_FAIL(15, "订单状态更新错误"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态错误"),

    PARAM_ERROR(1, "参数错误"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

    WECHAT_NOTIFY_MONEY_VERIFY_ERROR(21, "微信异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINISH_SUCCESS(23, "订单完结成功"),

    PRODUCT_STATUS_ERROR(24, "商品状态错误"),

    LOGIN_FAIL(25, "登录失败"),

    LOGOUT_SUCCESS(26, "登出成功"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
