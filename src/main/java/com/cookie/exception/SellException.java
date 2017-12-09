package com.cookie.exception;


import com.cookie.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by myseital  on 2017/11/20.
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
