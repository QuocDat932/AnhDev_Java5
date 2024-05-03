package com.quocdat.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String doGetViewIndex(Model model){
        model.addAttribute("currentView", "home");
        return "user/indexLayout";
    }
    @GetMapping("/ThongKe")
    public String doGetViewThongKe(Model model){
        model.addAttribute("currentView", "ThongKe");
        return "user/ThongKe/ThongKe";
    }
    @GetMapping("/BangDiem")
    public String doGetViewBangDiem(Model model){
        model.addAttribute("currentView", "BangDiem");
        return "user/BangDiem/BangDiem";
    }
    @GetMapping("/SinhVien")
    public String doGetViewSinhVien(Model model){
        model.addAttribute("currentView", "SinhVien");
        return "user/SinhVien/SinhVien";
    }
    @GetMapping("/LichHoc")
    public String doGetViewLichHoc(Model model){
        model.addAttribute("currentView", "LichHoc");
        return "user/LichHoc/LichHoc";
    }
    @GetMapping("/MonHoc")
    public String doGetViewMonHoc(Model model){
        model.addAttribute("currentView", "MonHoc");
        return "user/MonHoc/MonHoc";
    }
    @GetMapping({"/","/login"})
    public String doGetViewLogin () {
        return "pages/login";
    }
}
