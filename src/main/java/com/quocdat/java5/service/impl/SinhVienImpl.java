package com.quocdat.java5.service.impl;

import com.quocdat.java5.entity.SinhVien;
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
    public SinhVien getSinhVienByMSSV(String mssv) throws SQLException {
        return repo.getSinhViensByMssv(mssv);
    }


    @Transactional
    @Override
    public SinhVien postSaveSinhVien(SinhVien sinhVien) throws SQLException {
        repo.saveAndFlush(sinhVien);
        return repo.getSinhViensByMssv(sinhVien.getMssv());
    }
}
