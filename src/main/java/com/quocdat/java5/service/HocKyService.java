package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.data.model.HocKiM;

import java.util.List;

public interface HocKyService {
    List<HocKiM> getAllHocKy();

    int deleteHocKyByMaHocKy(String maHK);

    HocKiM saveHocKy(HocKiDto hocky);
}
