package com.cookie.utils;


import com.cookie.enums.CodeEnum;

/**
 * Created by myseital  on 2017/11/22.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {

        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
