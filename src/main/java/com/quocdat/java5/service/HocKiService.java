package com.quocdat.java5.service;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.data.model.HocKiM;

import java.util.List;

public interface HocKiService {
    List<HocKiM> getAllHocKy();

    int deleteHocKyByMaHocKy(String maHK);

    HocKiM saveHocKi(HocKiDto hocky);

    boolean existsHocKyByMaHk(String maHK);

    HocKiM updateHocKi(HocKiDto hocky);

    List<HocKiM> getListHocKyByTime(String time);
}
