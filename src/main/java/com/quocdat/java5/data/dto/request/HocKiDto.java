package com.quocdat.java5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HocKiDto {
    private String maHk;
    private String tenHocKi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
}
