package com.quocdat.java5.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class HomeController {
    @GetMapping({"/index", "/"})
    public String doGetViewIndex(final HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user", "");
        return "index";
    }
    @GetMapping("/login")
    public String doGetViewLogin(){
        return "pages/login";
    }
    @PostMapping("/login")
    public String doPostViewLogin(@RequestBody Map<String, String> data, final HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = data.get("role");
        session.setAttribute("user", role);
        return "redirect:/" + role.toLowerCase() + "/home";
    }
    @PostMapping("/logout")
    public String doPostViewLogout(final HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", "");
        return "redirect:/index";
    }
}
