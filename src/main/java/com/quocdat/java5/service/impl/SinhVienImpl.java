package com.quocdat.java5.service.impl;

import com.quocdat.java5.dto.request.SinhVienDto;
import com.quocdat.java5.entity.HocKi;
import com.quocdat.java5.entity.SinhVien;
import com.quocdat.java5.exception.AppException;
import com.quocdat.java5.exception.ErrorCode;
import com.quocdat.java5.mapper.SinhVienMapper;
import com.quocdat.java5.repository.SinhVienRepo;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SinhVienImpl implements SinhVienService {

    final SinhVienRepo repo;

    @Override
    public List<SinhVienDto> getAllSinhVien() throws AppException {
        List<SinhVien> sinhVienList = repo.findAll();
        return sinhVienList.stream()
                .map(SinhVienMapper::mapToSinhVienDto)
                .collect(Collectors.toList());
    }

    @Override
    public SinhVienDto getSinhVienByMSSV(String mssv) throws SQLException, AppException {
        if (!repo.existsSinhVienByMssv(mssv)) throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        SinhVienDto sinhVienDto = SinhVienMapper.mapToSinhVienDto(repo.getSinhViensByMssv(mssv));
        return sinhVienDto;
    }

    @Transactional
    @Override
    public SinhVienDto postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException, AppException {
        SinhVien sinhVienE = SinhVienMapper.mapToSinhVien(sinhVienDto);
        HocKi hocKi = SinhVienMapper.mapToHocKi(sinhVienDto.getHocKi());
        sinhVienE.setHocKi(hocKi);
        if (repo.existsSinhVienByMssv(sinhVienDto.getMssv())) throw new AppException(ErrorCode.STUDENT_EXISTED);
        SinhVien savedSinhVien = repo.saveAndFlush(sinhVienE);
        return SinhVienMapper.mapToSinhVienDto(savedSinhVien);
    }
}
