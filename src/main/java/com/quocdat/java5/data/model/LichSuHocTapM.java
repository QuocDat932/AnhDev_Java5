package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.HocKiE;
import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.MonHocE;
import com.quocdat.java5.data.entity.SinhVienE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LichSuHocTapM {
    private Long sysId;
    private SinhVienE mssv;
    private int lanThi;
    private Double diemThi;
    private String ketQua;
    private MonHocE monHoc;
    private HocKiE hocKi;

    public static LichSuHocTapM convertLichSuHocTapEToLichSuHocTapM(LichSuHocTapE lichSuHocTapE) {
        return LichSuHocTapM.builder()
                .sysId(lichSuHocTapE.getSysId())
                .mssv(lichSuHocTapE.getMssv())
                .lanThi(lichSuHocTapE.getLanThi())
                .diemThi(lichSuHocTapE.getDiemThi())
                .ketQua(lichSuHocTapE.getKetQua())
                .monHoc(lichSuHocTapE.getMonHoc())
                .hocKi(lichSuHocTapE.getHocKi())
                .build();
    }

    public static List<LichSuHocTapM> convertListLichSuHocTapEToListLichSuHocTapM(List<LichSuHocTapE> lichSuHocTapEList) {
        return lichSuHocTapEList.stream()
                .map(e -> convertLichSuHocTapEToLichSuHocTapM(e))
                .collect(Collectors.toList());
    }
}
