package com.quocdat.java5.service;

import com.quocdat.java5.dto.request.SinhVienRequestDto;
import com.quocdat.java5.dto.response.SinhVienResponseDto;
import com.quocdat.java5.exception.AppException;

import java.sql.SQLException;
import java.util.List;

public interface SinhVienService {
    /**
     * Lấy thông tin tất cả sinh viên
     * @return
     * @throws AppException
     */
    List<SinhVienResponseDto> getAllSinhVien() throws AppException;

    /**
     * Lấy thông tin sinh viên theo mã số sinh viên
     *
     * @param mssv Mã số sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienResponseDto getSinhVienByMSSV(String mssv) throws SQLException, AppException;

    /**
     * Lưu thông tin sinh viên
     *
     * @param sinhVienRequestDto Đối tượng chứa thông tin sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienResponseDto postSaveSinhVien(SinhVienRequestDto sinhVienRequestDto) throws SQLException, AppException;
}
