package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.LichSuHocTapDto;
import com.quocdat.java5.data.model.LichSuHocTapM;

import java.sql.SQLException;
import java.util.List;

public interface LichSuHoctapService {

    List<LichSuHocTapM> getAllLichSuHocTap() throws SQLException;

    List<LichSuHocTapM> getLichSuHocTapByMSSV(String mssv) throws SQLException;

    LichSuHocTapM postSaveLichSuHocTap(LichSuHocTapDto lichSuHocTapDto) throws SQLException;

    Boolean deleteLichSuHocTapBySysId(Long sysId) throws SQLException;

    List<LichSuHocTapM> getListLichSuHocTapByFilter(String mssv, String maMonHoc, String ketQua) throws SQLException;
}
