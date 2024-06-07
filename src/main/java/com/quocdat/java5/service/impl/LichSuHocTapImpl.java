package com.quocdat.java5.service.impl;

import com.quocdat.java5.convert.LichSuHocTapConvert;
import com.quocdat.java5.data.dto.request.LichSuHocTapDto;
import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.SinhVienE;
import com.quocdat.java5.data.model.LichSuHocTapM;
import com.quocdat.java5.repository.LichSuHocTapRepo;
import com.quocdat.java5.repository.SinhVienRepo;
import com.quocdat.java5.service.LichSuHoctapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LichSuHocTapImpl implements LichSuHoctapService {

    private final LichSuHocTapRepo repo;

    private final SinhVienRepo sinhVienRepo;

//    @Override
//    public List<LichSuHocTap> getListLichSuHocTapByMssv(String mssv) throws SQLException{
//        return repo.getLichSuHocTapByIdMssv(mssv);
//    }

    @Override
    public LichSuHocTapM getLichSuHocTapBySysId(Long sysId) throws SQLException {
        LichSuHocTapE lichSuHocTapE =  repo.getLichSuHocTapBySysId(sysId);
        return LichSuHocTapM.convertLichSuHocTapEToLichSuHocTapM(lichSuHocTapE);
    }

    @Override
    public List<LichSuHocTapM> getLichSuHocTapByMssv(String mssv) throws SQLException {
        SinhVienE sinhVienE = sinhVienRepo.getSinhViensByMssv(mssv);
        List<LichSuHocTapE> lichSuHocTapE = repo.getLichSuHocTapByMssv(sinhVienE);
        return LichSuHocTapM.convertListLichSuHocTapEToListLichSuHocTapM(lichSuHocTapE);
    }

    @Override
    public List<LichSuHocTapM> getAllLichSuHocTap() throws SQLException {
        List<LichSuHocTapE> lichSuHocTapList = repo.findAll();
        return LichSuHocTapM.convertListLichSuHocTapEToListLichSuHocTapM(lichSuHocTapList);
    }

    @Transactional
    @Override
    public LichSuHocTapM postSaveLichSuHocTap(LichSuHocTapDto lichSuHocTapDto) throws SQLException {
        return LichSuHocTapM.convertLichSuHocTapEToLichSuHocTapM(
                repo.save(LichSuHocTapConvert.convertLichSuHocTapDtoToLichSuHocTapE(lichSuHocTapDto))
        );
    }
}
