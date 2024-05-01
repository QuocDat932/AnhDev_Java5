package com.quocdat.java5.repository;

import com.quocdat.java5.entity.SinhVien;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienRepo extends JpaRepository<SinhVien, String> {
    SinhVien getSinhViensByMssv(String mssv);

}
