package com.quocdat.java5.repository;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.SinhVienE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuHocTapRepo extends JpaRepository<LichSuHocTapE, String> {
    List<LichSuHocTapE> getLichSuHocTapByMssv(SinhVienE mssv);

    LichSuHocTapE getLichSuHocTapBySysId(Long sysId);

//    LichSuHocTapE save(LichSuHocTapE lichSuHocTapE);
}
