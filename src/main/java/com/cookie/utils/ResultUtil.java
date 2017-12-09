package com.cookie.utils;

public class ResultUtil {

    public static<T> Result success(T t) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(t);
        return  result;
    }

    public static Result success() {
        return  success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
}
