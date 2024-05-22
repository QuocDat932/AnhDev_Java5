package com.quocdat.java5.mapper;

import com.quocdat.java5.dto.request.SinhVienDto;
import com.quocdat.java5.entity.HocKi;
import com.quocdat.java5.entity.SinhVien;

public class SinhVienMapper {
    public static SinhVienDto mapToSinhVienDto(SinhVien sinhVien) {
        String maHocKi = mapToHocKiE(sinhVien.getHocKi());
        return new SinhVienDto(
                sinhVien.getMssv(),
                sinhVien.getHoVaTen(),
                sinhVien.isGioiTinh(),
                sinhVien.getChuyenNganh(),
                maHocKi
        );
    }

    public static SinhVien mapToSinhVien(SinhVienDto sinhVienDto) {
        HocKi hocKi = mapToHocKi(sinhVienDto.getHocKi());
        return new SinhVien(
                sinhVienDto.getMssv(),
                sinhVienDto.getHoVaTen(),
                sinhVienDto.isGioiTinh(),
                sinhVienDto.getChuyenNganh(),
                hocKi
        );
    }

    public static HocKi mapToHocKi(String maHk) {
        return new HocKi(maHk);
    }

    static String mapToHocKiE(HocKi hocKi) {
        return hocKi.getMaHk();
    }
}
