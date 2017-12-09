package com.cookie.handler;


import com.cookie.common.config.ProjectUrlConfig;
import com.cookie.exception.MyException;
import com.cookie.exception.SellException;
import com.cookie.exception.SellerAuthorizeException;
import com.cookie.utils.Result;
import com.cookie.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException() {

        return new ModelAndView("redirect:".concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize").concat("?returnUrl=")
                .concat(projectUrlConfig.getSell()).concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public Result handlerSellerException(SellException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handlerMyxception() {

    }
}
