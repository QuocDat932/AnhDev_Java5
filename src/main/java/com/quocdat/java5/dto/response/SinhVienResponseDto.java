package com.quocdat.java5.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SinhVienResponseDto {
    private String mssv;
    private String hoVaTen;
    private boolean gioiTinh;
    private String chuyenNganh;
    private String hocKi;
}