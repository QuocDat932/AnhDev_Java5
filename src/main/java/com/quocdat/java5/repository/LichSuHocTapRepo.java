package com.quocdat.java5.repository;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.SinhVienE;
import com.quocdat.java5.data.model.LichSuHocTapM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LichSuHocTapRepo extends JpaRepository<LichSuHocTapE, String> {
    List<LichSuHocTapE> getLichSuHocTapByMssv(SinhVienE mssv);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lich_su_hoc_tap l WHERE l.sys_id = ?1", nativeQuery = true)
    void deleteLichSuHocTapBySysId(Long sysId);

    @Query(value = "SELECT * FROM lich_su_hoc_tap l WHERE l.mssv like %?1% AND l.ma_mon_hoc like %?2% AND l.ket_qua like %?3%", nativeQuery = true)
    List<LichSuHocTapE> getListLichSuHocTapByFilter(String mssv, String maMonHoc, String ketQua);
}
