package com.quocdat.java5.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinhVienRequestDto {
    private String mssv;
    private String hoVaTen;
    private boolean gioiTinh;
    private String chuyenNganh;
    private String hocKi;
}