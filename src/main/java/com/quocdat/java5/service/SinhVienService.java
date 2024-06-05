package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.SinhVienDto;
import com.quocdat.java5.data.entity.SinhVienE;
import com.quocdat.java5.data.model.SinhVienM;

import java.sql.SQLException;
import java.util.List;

public interface SinhVienService {
    /**
     * Lấy thông tin tất cả sinh viên
     * @return
     */
    List<SinhVienM> getAllSinhVien();

    /**
     * Lấy thông tin sinh viên theo mã số sinh viên
     *
     * @param mssv Mã số sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienM getSinhVienByMSSV(String mssv) throws SQLException;

    /**
     * Lưu thông tin sinh viên
     *
     * @param sinhVienDto Đối tượng chứa thông tin sinh viên
     * @return SinhVienDto
     * @throws SQLException
     */
    SinhVienM postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException;
}
