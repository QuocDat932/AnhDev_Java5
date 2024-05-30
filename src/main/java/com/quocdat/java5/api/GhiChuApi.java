package com.quocdat.java5.api;

import com.quocdat.java5.data.dto.GhiChuDto;
import com.quocdat.java5.service.GhiChuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/java05/ghiChu-api")
public class GhiChuApi {
    @Autowired
    private GhiChuService ghiChuServ;

    @GetMapping("/getGhiChuBySysTdUser")
    public ResponseEntity<?> doGetGhiChuBySysIdUser(@RequestParam("sysIdUser") Long sysIdUser, @RequestParam("month") Long month, @RequestParam("year") Long year) {
        Map<String,Object> result=new HashMap();
        try {
            result.put("success",true);
            result.put("data",ghiChuServ.getAllGhiChuBySysIdUser(sysIdUser, month, year));
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/postSaveGhiChu")
    public ResponseEntity<?> doSaveGhiChu(@RequestBody GhiChuDto ghiChuDto) throws SQLException {
        Map<String,Object> result=new HashMap();
        try {
            result.put("success",true);
            result.put("data",ghiChuServ.saveGhiChu(ghiChuDto));
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteGhiChu")
    public ResponseEntity<?> doDeleteGhiChuBySysId(@RequestParam("sysId") Long sysId) throws SQLException {

        Map<String,Object> result=new HashMap();
        try {
            Boolean success = ghiChuServ.deleteGhiChuBySysId(sysId);
            if (success) {
                result.put("success",true);
                result.put("data",1);
                result.put("message","Xóa thành công");
            } else {
                result.put("success",false);
                result.put("data",0);
                result.put("message","Không tìm thấy ghi chú để xóa");
            }
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",0);
            result.put("message","Xóa không thành công");
        }
        return ResponseEntity.ok(result);
    }
}
