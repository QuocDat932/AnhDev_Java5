package com.quocdat.java5.api;

import com.quocdat.java5.bean.SchoolInformation;
import com.quocdat.java5.bean.SchoolInformationMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/java5-b4/bean")
public class BeanApi {
    @Autowired
    @Qualifier("schoolInformationConfig")
    private SchoolInformation schoolBean;

    @Autowired
    private SchoolInformationMethod method;

    @GetMapping("/getBeanSchool")
    public ResponseEntity<?> doGetBeanSchool(){
        Map<String, Object> result = new HashMap<>();
        result.put("object", schoolBean);
        result.put("method", method.thuHocPhi());

        return ResponseEntity.ok(result);
    }
}
