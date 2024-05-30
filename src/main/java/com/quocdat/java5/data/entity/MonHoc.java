package com.quocdat.java5.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(schema = "project_final_java05", name = "mon_hoc")
public class MonHoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_mon_hoc")
    private String maMonHoc;

    @Column(name = "ten_mon_hoc")
    private String tenMonHoc;

    @Column(name = "so_tin_chi")
    private String soTinChi;


}
