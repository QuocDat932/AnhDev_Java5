package com.quocdat.java5.api;

import com.quocdat.java5.dto.SinhVienDto;
import com.quocdat.java5.service.SinhVienService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/java05/sinhvien-api")
public class SinhVienApi {

    @Autowired
    private SinhVienService sinhVienServ;

    @GetMapping("/getSinhVienByMSSV")
    public ResponseEntity<?> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv){
        Map<String, Object> result = new HashMap();
        try {
            result.put("status", true);
            result.put("message", "Call API Success");
            result.put("data", sinhVienServ.getSinhVienByMSSV(mssv));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Fail when call API /java05/sinhvien-api/getSinhVienByMSSV");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveSinhVien")
    public ResponseEntity<?> doPostSaveSinhVien(@RequestBody SinhVienDto sinhVien){
        Map<String, Object> result = new HashMap();
        try {
            result.put("status", true);
            result.put("message", "Call API Success");
            result.put("data", sinhVienServ.postSaveSinhVien(sinhVien));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Fail when call API /java05/sinhvien-api/saveSinhVien");
            result.put("data", null);
            log.error("Fail When Call API: Fail when call API /java05/sinhvien-api/saveSinhVien: ", e);
        }
        return ResponseEntity.ok(result);
    }
}
