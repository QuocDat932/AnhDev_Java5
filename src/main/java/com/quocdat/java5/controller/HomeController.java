package com.quocdat.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/home/index")
    public String doGetViewIndex(){
        return "user/index";
    }
    @GetMapping({"/","/login"})
    public String doGetViewLogin () {
        return "pages/login";
    }
}
