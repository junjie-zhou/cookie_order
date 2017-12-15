package com.cookie.controller;

import com.mao.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WebSocket webSocket;

    @GetMapping("/test")
    public ModelAndView test() {

        return new ModelAndView("common/test");
    }
}
