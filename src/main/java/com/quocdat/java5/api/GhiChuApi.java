package com.quocdat.java5.api;

import com.quocdat.java5.dto.ApiResponse;
import com.quocdat.java5.dto.GhiChuDto;
import com.quocdat.java5.service.GhiChuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/java05/ghiChu-api")
public class GhiChuApi {
    @Autowired
    private GhiChuService ghiChuServ;

    @GetMapping("/getGhiChuBySysTdUser")
    public ResponseEntity<?> doGetGhiChuBySysIdUser(@RequestParam("sysIdUser") Long sysIdUser,
                                                    @RequestParam("month") Long month,
                                                    @RequestParam("year") Long year){
        ApiResponse<List> result = new ApiResponse<>();
        try {
            result.setSuccess(true);
            result.setMessage("Lấy liệu thành công");
            result.setPayload(ghiChuServ.getAllGhiChuBySysIdUser(sysIdUser, month, year));
        } catch (Exception e){
            result.setSuccess(false);
            result.setMessage("Lấy liệu không thành công");
            result.setPayload(null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/postSaveGhiChu")
    public ResponseEntity<?> doSaveGhiChu(@RequestBody GhiChuDto ghiChuDto) throws SQLException {
        ApiResponse<Integer> result = new ApiResponse<>();
        try {
            result.setSuccess(ghiChuServ.saveGhiChu(ghiChuDto));
            result.setMessage("Lưu thông tin thành công");
            result.setPayload(1);
        } catch (Exception e){
            result.setSuccess(ghiChuServ.saveGhiChu(ghiChuDto));
            result.setMessage("Lưu thông tin không thành công");
            result.setPayload(0);
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/deleteGhiChu")
    public ResponseEntity<?> doDeleteGhiChuBySysId(@RequestParam("sysId") Long sysId) throws SQLException {
        ApiResponse<Integer> result = new ApiResponse<>();
        try {
            Boolean success = ghiChuServ.deleteGhiChuBySysId(sysId);
            if (success) {
                result.setSuccess(true);
                result.setMessage("Xóa thành công");
                result.setPayload(1);
            } else {
                result.setSuccess(false);
                result.setMessage("Không tìm thấy ghi chú để xóa");
                result.setPayload(0);
            }
        } catch (Exception e){
            result.setSuccess(false);
            result.setMessage("Xóa không thành công");
            result.setPayload(0);
        }
        return ResponseEntity.ok(result);
    }
}
