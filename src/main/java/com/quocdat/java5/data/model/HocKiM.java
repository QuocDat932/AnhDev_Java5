package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.HocKiE;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HocKiM {
    private String maHk;
    private String tenHocKi;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public static HocKiM convertHocKyEToHocKyM(HocKiE hocKiE){
        return HocKiM.builder()
                .maHk(hocKiE.getMaHk())
                .tenHocKi(hocKiE.getTenHocKi())
                .ngayBatDau(hocKiE.getNgayBatDau())
                .ngayKetThuc(hocKiE.getNgayKetThuc())
                .build();
    }

    public static List<HocKiM> convertListHocKyEToListHocKyM(List<HocKiE> listHocKyE){
        return listHocKyE
                .stream()
                .map(e -> convertHocKyEToHocKyM(e))
                .collect(Collectors.toList());
    }
}
