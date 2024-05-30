package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.GhiChuE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GhiChuM {
    private Long sysId;
    private String tenGhiChu;
    private String noiDung;
    private String ngay;
    private String mauSac;
    private Long sysIdUser;

    public static GhiChuM convertGhiChuEToGhiChuM(GhiChuE ghiChuE){
        return GhiChuM.builder()
                .sysId(ghiChuE.getSysId())
                .tenGhiChu(ghiChuE.getTenGhiChu())
                .noiDung(ghiChuE.getNoiDung())
                .ngay(ghiChuE.getNgay())
                .mauSac(ghiChuE.getMauSac())
                .sysIdUser(ghiChuE.getSysIdUser())
                .build();
    }

    public static List<GhiChuM> convertListGhiChuEToListGhiChuM(List<GhiChuE> ghiChuEList) {
        return ghiChuEList.stream()
                .map(e -> convertGhiChuEToGhiChuM(e))
                .collect(Collectors.toList());
    }
}
