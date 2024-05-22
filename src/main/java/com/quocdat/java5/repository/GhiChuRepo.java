package com.quocdat.java5.repository;

import com.quocdat.java5.entity.GhiChu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GhiChuRepo extends JpaRepository<GhiChu, Long> {
    @Query(value = "SELECT gc.sys_id, gc.ten_ghi_chu, gc.noi_dung, gc.ngay, gc.mau_sac, gc.sys_id_user FROM ghi_chu gc WHERE gc.sys_id_user = ?1 AND MONTH(gc.ngay) = ?2 AND YEAR(gc.ngay) = ?3", nativeQuery = true)
    List<GhiChu> findAllBySysIdUser (Long sysIdUser, Long month, Long year);

    GhiChu save (GhiChu ghiChu);

    void deleteById (Long sydId);
}
