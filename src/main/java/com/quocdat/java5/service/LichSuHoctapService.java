package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.LichSuHocTapDto;
import com.quocdat.java5.data.model.LichSuHocTapM;

import java.sql.SQLException;
import java.util.List;

public interface LichSuHoctapService {
//    List<LichSuHocTap> getListLichSuHocTapByMssv(String mssv) throws SQLException;

//    void postSaveLichSuHocTap(LichSuHocTap lichSuHocTap) throws SQLException;

//    LichSuHocTap getLichSuHocTapById(LichSuHocTapID id) throws SQLException;

//    List<LichSuHocTap> getAllLichSuHocTap() throws SQLException;

    /**
     * Lấy thông tin tất cả lịch sử học tập
     *
     * @return
     */
    List<LichSuHocTapM> getAllLichSuHocTap() throws SQLException;

    LichSuHocTapM getLichSuHocTapBySysId(Long sysId) throws SQLException;

    List<LichSuHocTapM> getLichSuHocTapByMssv(String mssv) throws SQLException;

    LichSuHocTapM postSaveLichSuHocTap(LichSuHocTapDto lichSuHocTapDto) throws SQLException;
}
