package com.quocdat.java5.service;

import com.quocdat.java5.dto.SinhVienDto;
import com.quocdat.java5.exception.AppException;

import java.sql.SQLException;

public interface SinhVienService {
    /**
     * Lấy thông tin sinh viên theo mã số sinh viên
     *
     * @param mssv Mã số sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienDto getSinhVienByMSSV(String mssv) throws SQLException, AppException;

    /**
     * Lưu thông tin sinh viên
     *
     * @param sinhVienDto Đối tượng chứa thông tin sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienDto postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException, AppException;
}
