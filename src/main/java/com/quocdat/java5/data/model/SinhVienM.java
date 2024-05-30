package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.HocKi;
import com.quocdat.java5.data.entity.SinhVienE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SinhVienM {
    private String mssv;
    private String hoVaTen;
    private boolean gioiTinh;
    private String chuyenNganh;
    private HocKi hocKi;

    public static SinhVienM convertSinhVienEToSinhVienM(SinhVienE sinhVienE){
        return SinhVienM.builder()
                .mssv(sinhVienE.getMssv())
                .hoVaTen(sinhVienE.getHoVaTen())
                .gioiTinh(sinhVienE.isGioiTinh())
                .chuyenNganh(sinhVienE.getChuyenNganh())
                .hocKi(sinhVienE.getHocKi())
                .build();
    }

    public static List<SinhVienM> convertListSinhVienEToListSinhVienM(List<SinhVienE> sinhVienEList) {
        return sinhVienEList.stream()
                .map(e -> convertSinhVienEToSinhVienM(e))
                .collect(Collectors.toList());
    }

}
