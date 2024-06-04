package com.quocdat.java5.convert;

import com.quocdat.java5.data.dto.request.SinhVienDto;
import com.quocdat.java5.data.entity.HocKiE;
import com.quocdat.java5.data.entity.SinhVienE;

public class SinhVienConvert {
    public static SinhVienE convertSinhVienDtotoSinhVienE(SinhVienDto sinhVienDto){
        return new SinhVienE(
            sinhVienDto.getMssv(),
            sinhVienDto.getHoVaTen(),
            sinhVienDto.isGioiTinh(),
            sinhVienDto.getChuyenNganh(),
            new HocKiE(sinhVienDto.getHocKi())
        );
    }
}
