package com.quocdat.java5.convert;

import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;

public class MonHocConvert {
    public static MonHocE convertMonHocDtoToMonHocE(MonHocDto monHocDto) {
        MonHocE monHocE = new MonHocE();
        monHocE.setMaMonHoc(monHocDto.getMaMonHoc());
        monHocE.setTenMonHoc(monHocDto.getTenMonHoc());
        monHocE.setSoTinChi(monHocDto.getSoTinChi());
        return monHocE;
    }
}
