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
    public List<SinhVienM> getListSinhVienByMSSV(String mssv) throws SQLException {
        return SinhVienM.convertListSinhVienEToListSinhVienM(repo.getListSinhVienByMssv(mssv));
    }

    @Transactional
    @Override
    public SinhVienM postSaveSinhVien(SinhVienDto sinhVienDto) throws SQLException{
        if(repo.existsSinhVienByMssv(sinhVienDto.getMssv())) throw new SQLException("Sinh Vien Already Exists");
        return SinhVienM.convertSinhVienEToSinhVienM(
                repo.save(SinhVienConvert.convertSinhVienDtotoSinhVienE(sinhVienDto)));
    }

    @Transactional
    @Override
    public SinhVienM putUpdateSinhVien(SinhVienDto sinhVienDto) throws SQLException{
        if(!repo.existsSinhVienByMssv(sinhVienDto.getMssv())) throw new SQLException("Sinh Vien Not Exists");
        return SinhVienM.convertSinhVienEToSinhVienM(
                repo.save(SinhVienConvert.convertSinhVienDtotoSinhVienE(sinhVienDto)));
    }

    @Transactional
    @Override
    public Boolean deleteSinhVienByMssv(String mssv) throws SQLException {
        try {
            repo.deleteById(mssv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> getAllChuyenNganh() throws SQLException {
        return repo.getAllChuyenNganh();
    }

    @Override
    public List<SinhVienM> getSinhVienByFilter(String mssv, String hoVaTen, String chuyenNganh) throws SQLException {
        return SinhVienM.convertListSinhVienEToListSinhVienM(
                repo.getSinhVienByFilter(mssv,hoVaTen,chuyenNganh)
        );
    }
}
