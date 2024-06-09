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

    @Query(value = "SELECT * FROM sinh_vien s WHERE s.mssv like %?1%", nativeQuery = true)
    List<SinhVienE> getListSinhVienByMssv(String mssv);

    @Query(value = "SELECT DISTINCT s.chuyen_nganh FROM sinh_vien s", nativeQuery = true)
    List<String> getAllChuyenNganh();

    @Query(value = "SELECT * FROM sinh_vien s WHERE s.chuyen_nganh like %?1%", nativeQuery = true)
    List<SinhVienE> getListSinhVienByChuyenNganh(String chuyenNganh);

    @Query(value = "SELECT * FROM sinh_vien s WHERE s.ho_va_ten like %?1%", nativeQuery = true)
    List<SinhVienE> getListSinhVienByHoTen(String hoVaTen);

    @Query(value = "SELECT * FROM sinh_vien s WHERE s.mssv like %?1% and s.ho_va_ten like %?2% and s.chuyen_nganh like %?3%", nativeQuery = true)
    List<SinhVienE> getSinhVienByFilter(String mssv, String hoVaTen, String chuyenNganh);
}
