package com.ruoyi.web.controller.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class test {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
