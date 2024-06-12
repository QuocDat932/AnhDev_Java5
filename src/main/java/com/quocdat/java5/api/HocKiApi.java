package com.quocdat.java5.api;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.service.HocKiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/hocki-api")
public class HocKiApi {
    private final HocKiService hocKyService;

    @GetMapping("/getAllHocKy")
    public ResponseEntity<?> doGetAllHocKy() {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",hocKyService.getAllHocKy());
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getHocKyByTime")
    public ResponseEntity<?> doGetHocKyByTime(@RequestParam String time) {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",hocKyService.getListHocKyByTime(time));
            result.put("message","Call api Success!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("saveHocKi")
    public ResponseEntity<?> doSaveHocKi(@RequestBody HocKiDto hocKi) {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",hocKyService.saveHocKi(hocKi));
            result.put("message","Lưu thành công!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Lưu thất bại!");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteHocKyByMaHocKy")
    public ResponseEntity<?> doDeleteHocKyByMaHocKy(@RequestParam("maHk") String maHK) {
        Map<String,Object> result =new HashMap();
        try {
            result.put("success",true);
            result.put("data",hocKyService.deleteHocKyByMaHocKy(maHK));
            result.put("message","Xóa thành công!");
        } catch (Exception e) {
            result.put("success",false);
            result.put("data",null);
            result.put("message","Call api Fail!");
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}
