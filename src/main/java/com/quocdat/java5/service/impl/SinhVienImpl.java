package com.quocdat.java5.service.impl;

import com.quocdat.java5.dto.SinhVienDto;
import com.quocdat.java5.entity.HocKi;
import com.quocdat.java5.entity.SinhVien;
import com.quocdat.java5.mapper.SinhVienMapper;
import com.quocdat.java5.repository.SinhVienRepo;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class SinhVienImpl implements SinhVienService {

    final SinhVienRepo repo;

    @Override
    public SinhVienDto getSinhVienByMSSV(String mssv) throws SQLException {
        SinhVienDto sinhVienDto = SinhVienMapper.mapToSinhVienDto(repo.getSinhViensByMssv(mssv));
        return sinhVienDto;
    }

    @Transactional
    @Override
    public SinhVienDto postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException {
        SinhVien sinhVienE = SinhVienMapper.mapToSinhVien(sinhVienDto);
        HocKi hocKi = SinhVienMapper.mapToHocKi(sinhVienDto.getHocKi());
        sinhVienE.setHocKi(hocKi);
        SinhVien savedSinhVien = repo.saveAndFlush(sinhVienE);
        return SinhVienMapper.mapToSinhVienDto(savedSinhVien);
    }
}
