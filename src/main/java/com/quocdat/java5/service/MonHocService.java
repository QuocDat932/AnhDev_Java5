package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;

import java.util.List;

public interface MonHocService {
    List<MonHocE> getAllMonHoc();
    MonHocE SaveMonHoc(MonHocDto monHoc);
    String deleteByMaMonHoc(String maMonHoc);
    List<MonHocE> getMonHocByMaMonHoc(String maMonHoc);
}
