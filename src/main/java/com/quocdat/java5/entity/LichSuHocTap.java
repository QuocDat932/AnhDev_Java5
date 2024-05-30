package com.quocdat.java5.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(schema = "project_final_java05", name = "lich_su_hoc_tap")
public class LichSuHocTap implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LichSuHocTapID id;

    @Column(name = "diem_thi")
    private Double diemThi;

    @Column(name = "ket_qua")
    private String ketQua;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "ma_hk", referencedColumnName = "ma_hk")
    private HocKiE hocKiE;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_mon_hoc", referencedColumnName = "ma_mon_hoc")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private MonHoc monHoc;

}
