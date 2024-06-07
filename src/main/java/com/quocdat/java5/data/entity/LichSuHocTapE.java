package com.quocdat.java5.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "project_final_java05", name = "lich_su_hoc_tap")
public class LichSuHocTapE implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sys_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "mssv", referencedColumnName = "mssv")
    private SinhVienE mssv;

    @Column(name = "lan_thi")
    private int lanThi;

    @Column(name = "diem_thi")
    private Double diemThi;

    @Column(name = "ket_qua")
    private String ketQua;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "ma_hk", referencedColumnName = "ma_hk")
    private HocKiE hocKi;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_mon_hoc", referencedColumnName = "ma_mon_hoc")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private MonHocE monHoc;
}
