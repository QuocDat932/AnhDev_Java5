package com.quocdat.java5.repository;

import com.quocdat.java5.entity.GhiChu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhiChuRepo extends JpaRepository<GhiChu, Long> {
    List<GhiChu> findAllBySysIdUser (Long sysIdUser);
}
