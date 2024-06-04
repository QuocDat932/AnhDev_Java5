package com.quocdat.java5.service.impl;

import com.quocdat.java5.convert.SinhVienConvert;
import com.quocdat.java5.data.dto.request.SinhVienDto;
import com.quocdat.java5.data.entity.SinhVienE;
import com.quocdat.java5.data.model.SinhVienM;
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
    public List<SinhVienM> getAllSinhVien() {
        List<SinhVienE> sinhVienList = repo.findAll();
            return SinhVienM.convertListSinhVienEToListSinhVienM(sinhVienList);
    }

    @Override
    public SinhVienM getSinhVienByMSSV(String mssv) throws SQLException {
        SinhVienM sinhVienM = SinhVienM.convertSinhVienEToSinhVienM(repo.getSinhViensByMssv(mssv));
        return sinhVienM;
    }

    @Transactional
    @Override
    public SinhVienE postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException{
        return repo.save(SinhVienConvert.convertSinhVienDtotoSinhVienE(sinhVienDto));
    }
}
