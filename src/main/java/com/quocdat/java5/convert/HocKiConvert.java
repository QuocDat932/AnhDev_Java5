package com.quocdat.java5.convert;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.data.entity.HocKiE;

public class HocKiConvert {
    public static HocKiE convertHocKiDtoToHocKiE(HocKiDto hocKiDto){
        return new HocKiE(
                hocKiDto.getMaHk()
                ,hocKiDto.getTenHocKi()
                ,hocKiDto.getNgayBatDau()
                ,hocKiDto.getNgayKetThuc()
        );
    }
}
