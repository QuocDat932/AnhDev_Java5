package com.quocdat.java5.api;

import com.quocdat.java5.dto.ApiResponse;
import com.quocdat.java5.dto.SinhVienDto;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/sinhvien-api")
public class SinhVienApi {

    private final SinhVienService sinhVienServ;

    @GetMapping("/getSinhVienByMSSV")
    public ApiResponse<SinhVienDto> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv) {
        ApiResponse<SinhVienDto> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setPayload(sinhVienServ.getSinhVienByMSSV(mssv));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setPayload(null);
            result.setError(e.getMessage());
            log.error("Error when calling API /java05/sinhvien-api/getSinhVienByMSSV: ", e);
        }
        return result;
    }

    @PostMapping("/saveSinhVien")
    public ApiResponse<?> doPostSaveSinhVien(@RequestBody SinhVienDto sinhVien) {
        ApiResponse<SinhVienDto> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setPayload(sinhVienServ.postSaveSinhVien(sinhVien));
            System.out.println(sinhVienServ.postSaveSinhVien(sinhVien));
            result.setId(sinhVien.getMssv());
            System.out.println(sinhVien.getMssv());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setPayload(null);
            result.setError(e.getMessage());
            log.error("Fail When Call API: Fail when call API /java05/sinhvien-api/saveSinhVien: ", e);
        }
        return result;
    }
}
