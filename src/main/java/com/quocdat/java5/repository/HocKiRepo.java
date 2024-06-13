package com.quocdat.java5.repository;

import com.quocdat.java5.data.entity.HocKiE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HocKiRepo extends JpaRepository<HocKiE, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM HocKiE h WHERE h.maHk = ?1")
    int deleteHocKy(String maHocKi);

    boolean existsHocKiByMaHk(String maHk);

    @Query(value = "SELECT h.ma_hk, h.ten_hoc_ki, h.bat_dau, h.ket_thuc FROM hoc_ki h WHERE ?1 BETWEEN h.bat_dau AND h.ket_thuc",nativeQuery = true)
    List<HocKiE> getListHocKiByTime(String time);
}
