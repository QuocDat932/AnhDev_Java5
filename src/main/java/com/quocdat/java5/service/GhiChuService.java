package com.quocdat.java5.service;

import com.quocdat.java5.dto.GhiChuDto;

import java.sql.SQLException;
import java.util.List;

public interface GhiChuService {
    List<GhiChuDto> getAllGhiChuBySysIdUser (Long sysIdUser) throws SQLException;
}
