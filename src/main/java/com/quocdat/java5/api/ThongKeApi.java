package com.quocdat.java5.api;

import com.quocdat.java5.service.ThongKeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/thongke-api")
public class ThongKeApi {

    private final ThongKeService thongKeService;
    @GetMapping("/getPassFailResult")
    public ResponseEntity<?> doGetPassFailResult(@RequestParam ("maMonHoc") String maMonHoc,
                                                      @RequestParam("maHocKy") String maHocKy) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("message", "call Api success");
            result.put("data", thongKeService.getPassFailRates(maMonHoc,maHocKy));
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "fail call Api");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }
}
