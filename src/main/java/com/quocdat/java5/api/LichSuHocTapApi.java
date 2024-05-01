package com.quocdat.java5.api;

import com.quocdat.java5.entity.LichSuHocTap;
import com.quocdat.java5.service.LichSuHoctapService;
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
@RequestMapping("/java05/lichsuhoctap-api")
public class LichSuHocTapApi {

    @Autowired
    private LichSuHoctapService lichsuhoctapServ;
    @GetMapping("/getLichSuHocTapByMSSV")
    public ResponseEntity<?> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv){
        Map<String, Object> result = new HashMap();
        try {
            result.put("status", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.getListLichSuHocTapByMssv(mssv));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/getLichSuHocTapByMSSV");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveLichSuHocTap")
    public ResponseEntity<?> doPostSaveLichSuHocTap(@RequestBody LichSuHocTap lichSuHocTap){
        Map<String, Object> result = new HashMap();
        try {
            lichsuhoctapServ.postSaveLichSuHocTap(lichSuHocTap);
            result.put("status", true);
            result.put("message", "Call API Success");
            result.put("data", lichSuHocTap);
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/saveLichSuHocTap");
            result.put("data", null);
            log.error("Fail When saveLichSuHocTap: ", e);
        }
        return ResponseEntity.ok(result);
    }

}
