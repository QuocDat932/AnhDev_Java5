package com.quocdat.java5.api;

import com.quocdat.java5.data.dto.request.SinhVienDto;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/sinhvien-api")
public class SinhVienApi {

    private final SinhVienService sinhVienServ;

    @GetMapping("/getAllSinhVien")
    public ResponseEntity<?> getAllSinhVien() {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",sinhVienServ.getAllSinhVien());
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getSinhVienByMSSV")
    public ResponseEntity<?> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv) {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",sinhVienServ.getSinhVienByMSSV(mssv));
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
            log.error("Error when calling API /java05/sinhvien-api/getSinhVienByMSSV: ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveSinhVien")
    public ResponseEntity<?> doPostSaveSinhVien(@RequestBody SinhVienDto sinhVien) {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",sinhVienServ.postSaveSinhVien(sinhVien));
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
            log.error("Error when calling API /java05/sinhvien-api/saveSinhVien: ", e);
        }
        return ResponseEntity.ok(result);

    }
}
