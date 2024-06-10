package com.quocdat.java5.repository;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.model.ThongKeM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThongKeRepo extends JpaRepository<LichSuHocTapE, String> {


    @Query(value ="SELECT * FROM lich_su_hoc_tap WHERE ma_mon_hoc = ?1 AND ma_hk = ?2", nativeQuery = true)
    List<LichSuHocTapE> getPassFailRates(String maMonHoc, String maHK);
}
