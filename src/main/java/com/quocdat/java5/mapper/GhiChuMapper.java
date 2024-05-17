package com.quocdat.java5.mapper;

import com.quocdat.java5.dto.GhiChuDto;
import com.quocdat.java5.entity.AccountE;
import com.quocdat.java5.entity.GhiChu;

import java.util.List;
import java.util.stream.Collectors;

public class GhiChuMapper {
    public static GhiChuDto mapToGhiChuDto(GhiChu ghiChu) {
        return new GhiChuDto(
                ghiChu.getSysId(),
                ghiChu.getTenGhiChu(),
                ghiChu.getNoiDung(),
                ghiChu.getNgay(),
                ghiChu.getMauSac(),
                ghiChu.getSysIdUser()
        );
    }
    public static List<GhiChuDto> mapToListGhiChuDto(List<GhiChu> ghiChuList) {
        return ghiChuList.stream()
                .map(e -> mapToGhiChuDto(e))
                .collect(Collectors.toList());
    }
    public static GhiChu mapToGhiChu(GhiChuDto ghiChuDto) {
        return new GhiChu(
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
