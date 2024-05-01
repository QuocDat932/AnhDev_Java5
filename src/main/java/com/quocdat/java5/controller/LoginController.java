package com.quocdat.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
    @PostMapping("/login")
    public Object login(@RequestBody @Validated Object account) {
        return "pages/login";
    }
}
