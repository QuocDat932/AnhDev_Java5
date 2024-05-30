package com.quocdat.java5.service.impl;

import com.quocdat.java5.data.dto.request.SinhVienDto;
import com.quocdat.java5.data.entity.SinhVienE;
import com.quocdat.java5.data.model.SinhVienM;
import com.quocdat.java5.exception.AppException;
import com.quocdat.java5.exception.ErrorCode;
import com.quocdat.java5.repository.SinhVienRepo;
import com.quocdat.java5.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SinhVienImpl implements SinhVienService {

    final SinhVienRepo repo;

    @Override
    public List<SinhVienM> getAllSinhVien() throws AppException {
        List<SinhVienE> sinhVienList = repo.findAll();
            return SinhVienM.convertListSinhVienEToListSinhVienM(sinhVienList);
    }

    @Override
    public SinhVienM getSinhVienByMSSV(String mssv) throws SQLException, AppException {
        if (!repo.existsSinhVienByMssv(mssv))
        {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        SinhVienM sinhVienM = SinhVienM.convertSinhVienEToSinhVienM(repo.getSinhViensByMssv(mssv));
        return sinhVienM;
    }

    @Transactional
    @Override
    public SinhVienE postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException, AppException {
//        SinhVienE sinhVienE = SinhVienMapper.mapToSinhVien(sinhVienDto);
//        HocKi hocKi = SinhVienMapper.mapToHocKi(sinhVienDto.getHocKi());
//        sinhVienE.setHocKi(hocKi);
//        if (repo.existsSinhVienByMssv(sinhVienDto.getMssv())) throw new AppException(ErrorCode.STUDENT_EXISTED);
//        SinhVienE savedSinhVien = repo.saveAndFlush(sinhVienE);
        return null;
    }
}
