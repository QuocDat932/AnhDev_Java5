package com.quocdat.java5.api;

import com.quocdat.java5.dto.response.ApiResponse;
import com.quocdat.java5.dto.request.SinhVienRequestDto;
import com.quocdat.java5.dto.response.SinhVienResponseDto;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/java05/sinhvien-api")
public class SinhVienApi {

    private final SinhVienService sinhVienServ;

    @GetMapping("/getAllSinhVien")
    public ApiResponse<List<SinhVienResponseDto>> getAllSinhVien() {
        ApiResponse<List<SinhVienResponseDto>> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setPayload(sinhVienServ.getAllSinhVien());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
        }
        return result;
    }

    @GetMapping("/getSinhVienByMSSV")
    public ApiResponse<SinhVienResponseDto> doGetSinhVienByMSSV(@RequestParam("mssv") String mssv) {
        ApiResponse<SinhVienResponseDto> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setPayload(sinhVienServ.getSinhVienByMSSV(mssv));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
            log.error("Error when calling API /java05/sinhvien-api/getSinhVienByMSSV: ", e);
        }
        return result;
    }

    @PostMapping("/saveSinhVien")
    public ApiResponse<SinhVienResponseDto> doPostSaveSinhVien(@RequestBody SinhVienRequestDto sinhVien) {
        ApiResponse<SinhVienResponseDto> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setPayload(sinhVienServ.postSaveSinhVien(sinhVien));
            result.setId(sinhVien.getMssv());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setError(e.getMessage());
            log.error("Fail When Call API: Fail when call API /java05/sinhvien-api/saveSinhVien: ", e);
        }
        return result;
    }
}
