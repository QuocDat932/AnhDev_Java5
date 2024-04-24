package com.quocdat.java5.api;

import com.quocdat.java5.dto.SinhVienDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/java5-b3")
public class B3ValidationApi {

    @PostMapping("/validation-server-side")
    public ResponseEntity<?> doGetValidationServerSide(@Valid @RequestBody SinhVienDTO sinhVien){
            Map<String, Object> resultApi = new HashMap<>();
            resultApi.put("status", true);
            resultApi.put("message","Call Api Success");
            resultApi.put("data", sinhVien);
            return ResponseEntity.ok(resultApi);
    }
}
