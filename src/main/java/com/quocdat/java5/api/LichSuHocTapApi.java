package com.quocdat.java5.api;

import com.quocdat.java5.data.dto.request.LichSuHocTapDto;
import com.quocdat.java5.service.LichSuHoctapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/java05/lichsuhoctap-api")
@RequiredArgsConstructor
public class LichSuHocTapApi {

    private final LichSuHoctapService lichsuhoctapServ;

    @GetMapping("/getLichSuHocTapByMSSV")
    public ResponseEntity<?> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv) {
        Map<String, Object> result = new HashMap();
        try {
            result.put("success", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.getLichSuHocTapByMssv(mssv));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/getLichSuHocTapByMSSV");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllLichSuHocTap")
    public ResponseEntity<?> doGetAllLichSuHocTap() {
        Map<String, Object> result = new HashMap();
        try {
            result.put("success", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.getAllLichSuHocTap());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/getAllLichSuHocTap");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("getListBangDiemByFilter")
    public ResponseEntity<?> doGetListBangDiemByFilter(@RequestParam("mssv") String mssv,
                                                       @RequestParam("maMonHoc") String maMonHoc,
                                                       @RequestParam("ketQua") String ketQua) {
        Map<String, Object> result = new HashMap();
        try {
            result.put("success", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.getListLichSuHocTapByFilter(mssv, maMonHoc, ketQua));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/getAllLichSuHocTap");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveLichSuHocTap")
    public ResponseEntity<?> doPostSaveLichSuHocTap(@RequestBody LichSuHocTapDto lichSuHocTapDto) {
        Map<String, Object> result = new HashMap();
        try {
            result.put("success", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.postSaveLichSuHocTap(lichSuHocTapDto));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/saveLichSuHocTap");
            result.put("data", null);
            log.error("Fail When saveLichSuHocTap: ", e);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteLichSuHocTapBySysId")
    public ResponseEntity<?> doDeleteLichSuHocTapBySysId(@RequestParam("sysId") Long sysId) {
        Map<String, Object> result = new HashMap();
        try {
            result.put("success", true);
            result.put("message", "Call API Success");
            result.put("data", lichsuhoctapServ.deleteLichSuHocTapBySysId(sysId));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when call API /java05/lichsuhoctap-api/deleteLichSuHocTapBySysId");
            result.put("data", null);
            log.error("Fail When saveLichSuHocTap: ", e);
        }
        return ResponseEntity.ok(result);
    }
}