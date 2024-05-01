package com.quocdat.java5.service;

import com.quocdat.java5.entity.SinhVien;
import java.sql.SQLException;

public interface SinhVienService {
    SinhVien getSinhVienByMSSV(String mssv) throws SQLException;

    SinhVien postSaveSinhVien(SinhVien sinhVien) throws SQLException;

}
