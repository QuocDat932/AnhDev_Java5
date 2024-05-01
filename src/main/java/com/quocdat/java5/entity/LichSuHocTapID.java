package com.quocdat.java5.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
public class LichSuHocTapID implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sysId;
    private String mssv;
    private String lanThi;

}

