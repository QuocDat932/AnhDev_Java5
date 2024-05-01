package com.quocdat.java5.service.impl;

import com.quocdat.java5.entity.LichSuHocTap;
import com.quocdat.java5.repository.LichSuHocTapRepo;
import com.quocdat.java5.service.LichSuHoctapService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LichSuHocTapImpl implements LichSuHoctapService {

    @Autowired
    private LichSuHocTapRepo repo;
    @Override
    public List<LichSuHocTap> getListLichSuHocTapByMssv(String mssv) throws SQLException{
        return repo.getLichSuHocTapByIdMssv(mssv);
    };

    @Transactional
    @Override
    public void postSaveLichSuHocTap(LichSuHocTap lichSuHocTap) throws SQLException {
        repo.saveAndFlush(lichSuHocTap);
    }
}
