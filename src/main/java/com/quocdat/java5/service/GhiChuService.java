package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.GhiChuDto;
import com.quocdat.java5.data.model.GhiChuM;

import java.sql.SQLException;
import java.util.List;

public interface GhiChuService {
    List<GhiChuM> getAllGhiChuBySysIdUser (Long sysIdUser, Long month, Long year) throws SQLException;
    Boolean saveGhiChu(GhiChuDto ghiChuDto) throws SQLException;
    Boolean deleteGhiChuBySysId(Long sysId) throws SQLException;
}
