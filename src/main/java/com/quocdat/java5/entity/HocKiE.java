package com.quocdat.java5.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "project_final_java05", name = "hoc_ki")
public class HocKiE implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_hk")
    private String maHk;

    @Column(name = "ten_hoc_ki")
    private String tenHocKi;

    @Column(name = "bat_dau")
    private Date ngayBatDau;

    @Column(name = "ket_thuc")
    private Date ngayKetThuc;

    @JsonCreator
    public HocKiE(@JsonProperty("ma_hk") String maHk) {
        this.maHk = maHk;
    }
}
