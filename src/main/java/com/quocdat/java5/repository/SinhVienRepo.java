package com.quocdat.java5.repository;

import com.quocdat.java5.entity.SinhVienE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienRepo extends JpaRepository<SinhVienE, String> {
    SinhVienE getSinhViensByMssv(String mssv);

    boolean existsSinhVienByMssv(String mssv);
}
