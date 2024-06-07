package com.quocdat.java5.convert;

import com.quocdat.java5.data.dto.GhiChuDto;
import com.quocdat.java5.data.entity.GhiChuE;

public class GhiChuConvert {
    public static GhiChuE convertGhiChuDtoToGhiChuE(GhiChuDto ghiChuDto){
        return new GhiChuE(
                ghiChuDto.getSysId(),
                ghiChuDto.getTenGhiChu(),
                ghiChuDto.getNoiDung(),
                ghiChuDto.getNgay(),
                ghiChuDto.getMauSac(),
                ghiChuDto.getSysIdUser()
        );
    }
}
