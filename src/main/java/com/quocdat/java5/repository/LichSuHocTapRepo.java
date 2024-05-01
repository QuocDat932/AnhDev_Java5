package com.quocdat.java5.repository;

import com.quocdat.java5.entity.LichSuHocTap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuHocTapRepo extends JpaRepository<LichSuHocTap, String> {
    List<LichSuHocTap> getLichSuHocTapByIdMssv(String mssv);
}
