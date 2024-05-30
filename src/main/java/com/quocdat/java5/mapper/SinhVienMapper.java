package com.quocdat.java5.mapper;

import com.quocdat.java5.dto.request.SinhVienRequestDto;
import com.quocdat.java5.dto.response.SinhVienResponseDto;
import com.quocdat.java5.entity.HocKiE;
import com.quocdat.java5.entity.SinhVienE;

public class SinhVienMapper {
    public static SinhVienResponseDto mapToSinhVienDto(SinhVienE sinhVienE) {
        String maHocKi = mapToHocKiE(sinhVienE.getHocKiE());
        return new SinhVienResponseDto(
                sinhVienE.getMssv(),
                sinhVienE.getHoVaTen(),
                sinhVienE.isGioiTinh(),
                sinhVienE.getChuyenNganh(),
                maHocKi
        );
    }

    public static SinhVienE mapToSinhVien(SinhVienRequestDto sinhVienRequestDto) {
        HocKiE hocKiE = mapToHocKi(sinhVienRequestDto.getHocKi());
        return new SinhVienE(
                sinhVienRequestDto.getMssv(),
                sinhVienRequestDto.getHoVaTen(),
                sinhVienRequestDto.isGioiTinh(),
                sinhVienRequestDto.getChuyenNganh(),
                hocKiE
        );
    }

    public static HocKiE mapToHocKi(String maHk) {
        return new HocKiE(maHk);
    }

    static String mapToHocKiE(HocKiE hocKiE) {
        return hocKiE.getMaHk();
    }
}
