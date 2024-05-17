package com.quocdat.java5.api;

import com.quocdat.java5.service.GhiChuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/java05/ghiChu-api")
public class GhiChuApi {
    @Autowired
    private GhiChuService ghiChuServ;
    @GetMapping("/getGhiChuBySysTdUser")
    public ResponseEntity<?> doGetGhiChuBySysIdUser(@RequestParam("sysIdUser") Long sysIdUser){
        Map<String, Object> result = new HashMap();
        try {
            result.put("status", true);
            result.put("message", "Call API Success");
            result.put("data", ghiChuServ.getAllGhiChuBySysIdUser(sysIdUser));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Fail when call API /java05/ghiChu-api/getGhiChuBySysIdUser");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
