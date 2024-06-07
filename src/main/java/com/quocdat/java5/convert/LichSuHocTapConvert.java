package com.quocdat.java5.convert;

import com.quocdat.java5.data.dto.request.LichSuHocTapDto;
import com.quocdat.java5.data.entity.HocKiE;
import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.MonHocE;
import com.quocdat.java5.data.entity.SinhVienE;

public class LichSuHocTapConvert {
    public static LichSuHocTapE convertLichSuHocTapDtoToLichSuHocTapE(LichSuHocTapDto lichSuHocTapDto){
        return new LichSuHocTapE(
                lichSuHocTapDto.getSysId(),
                new SinhVienE(lichSuHocTapDto.getMssv()),
                lichSuHocTapDto.getLanThi(),
                lichSuHocTapDto.getDiemThi(),
                lichSuHocTapDto.getKetQua(),
                new HocKiE(lichSuHocTapDto.getHocKi()),
                new MonHocE(lichSuHocTapDto.getMonHoc())
        );
    }
}
