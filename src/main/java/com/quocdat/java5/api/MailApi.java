package com.quocdat.java5.api;

import com.quocdat.java5.model.MailModel;
import com.quocdat.java5.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MailApi {
    private final MailService mailService;
    @PostMapping("/sendMailWithCustomBody")
    public Map<?, ?> doPostSendMailWithCustomBody(@RequestBody MailModel mailModel) {
        var resultMap = new HashMap<>();
        try {
            mailService.sendMail(mailModel);
            resultMap.put("success", true);
        } catch (Exception e) {
            resultMap.put("success", false);
            throw new RuntimeException(e);
        }
        return resultMap;
    }

    @PostMapping("/sendMail")
    public Map<?, ?> doPostSendMail() {
        var resultMap = new HashMap<String, Object>();
        try {
            mailService.sendMail(new MailModel());
            resultMap.put("success", true);
        } catch (Exception e) {
            resultMap.put("success", false);
            throw new RuntimeException(e);
        }
        return resultMap;
    }
}
