package com.quocdat.java5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichSuHocTapDto {
    private Long sysId = null;
    private String mssv;
    private int lanThi;
    private Double diemThi;
    private String ketQua;
    private String monHoc;
    private String hocKi;
}
