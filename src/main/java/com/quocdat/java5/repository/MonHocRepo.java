package com.quocdat.java5.repository;

import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonHocRepo extends JpaRepository<MonHocE, String> {
    @Modifying
    @Transactional
    @Query(value = "Delete from mon_hoc where ma_mon_hoc=?1", nativeQuery = true)
    int deleteByMaMonHoc(String maMonHoc);
    @Query(value = "select mh.ma_mon_hoc, mh.ten_mon_hoc, mh.so_tin_chi from mon_hoc mh where mh.ma_mon_hoc like ?1%", nativeQuery = true)
    List<MonHocE> getMonHocByMaMonHoc(String maMonHoc);
    MonHocE save(MonHocDto monHocDto);
}
