package com.quocdat.java5.data.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "project_final_java05", name = "mon_hoc")
public class MonHocE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_mon_hoc")
    private String maMonHoc;

    @Column(name = "ten_mon_hoc")
    private String tenMonHoc;

    @Column(name = "so_tin_chi")
    private String soTinChi;

    @JsonCreator
    public MonHocE(@JsonProperty("maMonHoc") String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }
}
