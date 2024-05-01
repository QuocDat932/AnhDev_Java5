package com.quocdat.java5.service;

import com.quocdat.java5.entity.LichSuHocTap;

import java.sql.SQLException;
import java.util.List;

public interface LichSuHoctapService {
    List<LichSuHocTap> getListLichSuHocTapByMssv(String mssv) throws SQLException;

    void postSaveLichSuHocTap(LichSuHocTap lichSuHocTap) throws SQLException;

}
