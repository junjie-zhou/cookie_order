package com.cookie.controller;

import com.cookie.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WebSocket webSocket;

    @GetMapping("/test")
    public Map<String,String> getTest(){
        Map<String ,String> map =new HashMap<String,String>();
        map.put("A","XXXXX");
        map.put("code" ,"0");
        return  map;
    }
}
