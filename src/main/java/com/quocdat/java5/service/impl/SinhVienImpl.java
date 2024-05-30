package com.quocdat.java5.service.impl;

import com.quocdat.java5.dto.request.SinhVienRequestDto;
import com.quocdat.java5.dto.response.SinhVienResponseDto;
import com.quocdat.java5.entity.HocKiE;
import com.quocdat.java5.entity.SinhVienE;
import com.quocdat.java5.exception.AppException;
import com.quocdat.java5.exception.ErrorCode;
import com.quocdat.java5.mapper.SinhVienMapper;
import com.quocdat.java5.repository.SinhVienRepo;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SinhVienImpl implements SinhVienService {

    final SinhVienRepo repo;

    @Override
    public List<SinhVienResponseDto> getAllSinhVien() throws AppException {
        List<SinhVienE> sinhVienEList = repo.findAll();
        return sinhVienEList.stream().map(SinhVienMapper::mapToSinhVienDto).collect(Collectors.toList());
    }

    @Override
    public SinhVienResponseDto getSinhVienByMSSV(String mssv) throws SQLException, AppException {
        if (!repo.existsSinhVienByMssv(mssv)) throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        SinhVienResponseDto sinhVienResponseDto = SinhVienMapper.mapToSinhVienDto(repo.getSinhViensByMssv(mssv));
        return sinhVienResponseDto;
    }

    @Transactional
    @Override
    public SinhVienResponseDto postSaveSinhVien(SinhVienRequestDto sinhVienRequestDto) throws SQLException, AppException {
        SinhVienE sinhVienE = SinhVienMapper.mapToSinhVien(sinhVienRequestDto);
        HocKiE hocKiE = SinhVienMapper.mapToHocKi(sinhVienRequestDto.getHocKi());
        sinhVienE.setHocKiE(hocKiE);
        if (repo.existsSinhVienByMssv(sinhVienRequestDto.getMssv())) throw new AppException(ErrorCode.STUDENT_EXISTED);
        SinhVienE savedSinhVienE = repo.saveAndFlush(sinhVienE);
        return SinhVienMapper.mapToSinhVienDto(savedSinhVienE);
    }
}
