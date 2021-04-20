package com.ruoyi.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset/test")
public class testContrller {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
