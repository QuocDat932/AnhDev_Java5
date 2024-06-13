package com.quocdat.java5.api;

import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.service.MonHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/monhoc-api")

public class MonHocApi {

    private final MonHocService monHocService;

    @GetMapping("/getAllMonHoc")
    public ResponseEntity<?> getAllMonHoc() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call api Success!");
            result.put("data", monHocService.getAllMonHoc());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "fali when Call api");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getMonHocByMaMonHoc")
    public ResponseEntity<?> getMonHocByMaMonHoc(@RequestParam ("maMonHoc") String maMonHoc ) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Call api Success!");
            result.put("data", monHocService.getMonHocByMaMonHoc(maMonHoc));
        }catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail when Call api");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/postSaveMonHoc")
    public ResponseEntity<?> postSaveMonHoc(@RequestBody MonHocDto monHoc){
        HashMap<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "Call api Success!");
            result.put("data", monHocService.postSaveMonHoc(monHoc));
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "fail when Call api");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/deleteByMaMonHoc")
    public ResponseEntity<?> deleteByMaMonHoc(@RequestParam ("maMonHoc") String maMonHoc) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Call api Success!");
        result.put("data", monHocService.deleteByMaMonHoc(maMonHoc));
        return ResponseEntity.ok(result);
    }
}
