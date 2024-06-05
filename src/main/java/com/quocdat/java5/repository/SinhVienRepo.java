package com.quocdat.java5.repository;

import com.quocdat.java5.data.entity.SinhVienE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepo extends JpaRepository<SinhVienE, String> {
    SinhVienE getSinhViensByMssv(String mssv);

    boolean existsSinhVienByMssv(String mssv);

    @Query(value = "SELECT DISTINCT s.chuyen_nganh FROM sinh_vien s", nativeQuery = true)
    List<String> getAllChuyenNganh();
}
