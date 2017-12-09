package com.cookie.enums;

import lombok.Getter;


@Getter
public enum OrderStatus implements CodeEnum {
    NEW(0, "新订单"),
    FINISH(1, "完成"),
    CANCEL(2, "已取消");

    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
