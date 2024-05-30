package com.quocdat.java5.mapper;

import com.quocdat.java5.dto.request.GhiChuDto;
import com.quocdat.java5.entity.AccountE;
import com.quocdat.java5.entity.GhiChuE;

import java.util.List;
import java.util.stream.Collectors;

public class GhiChuMapper {
    public static GhiChuDto mapToGhiChuDto(GhiChuE ghiChuE) {
        return new GhiChuDto(
                ghiChuE.getSysId(),
                ghiChuE.getTenGhiChu(),
                ghiChuE.getNoiDung(),
                ghiChuE.getNgay(),
                ghiChuE.getMauSac(),
                ghiChuE.getSysIdUser()
        );
    }
    public static List<GhiChuDto> mapToListGhiChuDto(List<GhiChuE> ghiChuEList) {
        return ghiChuEList.stream()
                .map(e -> mapToGhiChuDto(e))
                .collect(Collectors.toList());
    }
    public static GhiChuE mapToGhiChu(GhiChuDto ghiChuDto) {
        return new GhiChuE(
                ghiChuDto.getSysId(),
                ghiChuDto.getTenGhiChu(),
                ghiChuDto.getNoiDung(),
                ghiChuDto.getNgay(),
                ghiChuDto.getMauSac(),
                ghiChuDto.getSysIdUser()
        );
    }
    static Long maptoAccountE(AccountE accountE) {
        return accountE.getSysId();
    }
}
