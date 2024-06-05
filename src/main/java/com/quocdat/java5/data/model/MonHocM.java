package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.MonHocE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonHocM {
    private String maMonHoc;
    private String tenMonHoc;
    private String soTinChi;

    public static MonHocM convertMonHoctoMonHocM(MonHocE monHoc) {
        return MonHocM.builder()
                .maMonHoc(monHoc.getMaMonHoc())
                .tenMonHoc(monHoc.getTenMonHoc())
                .soTinChi(monHoc.getSoTinChi())
                .build();
    }
    public static List<MonHocM> convertListMonHocToListMonHocM(List<MonHocE> monHocList) {
        return  monHocList.stream()
                .map(e -> convertMonHoctoMonHocM(e))
                .collect(Collectors.toList());
    }
}
