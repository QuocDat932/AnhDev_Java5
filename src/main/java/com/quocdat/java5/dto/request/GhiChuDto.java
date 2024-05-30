package com.quocdat.java5.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GhiChuDto {
    private Long sysId;
    private String tenGhiChu;
    private String noiDung;
    private String ngay;
    private String mauSac;
    private Long sysIdUser;
}
