package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;
import com.quocdat.java5.data.model.HocKiM;
import com.quocdat.java5.data.model.MonHocM;

import java.util.List;

public interface MonHocService {
    List<MonHocM> getAllMonHoc();
    MonHocM postSaveMonHoc(MonHocDto monHoc);
    String deleteByMaMonHoc(String maMonHoc);
    List<MonHocM> getMonHocByMaMonHoc(String maMonHoc);
}
