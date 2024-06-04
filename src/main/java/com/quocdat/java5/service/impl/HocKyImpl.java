package com.quocdat.java5.service.impl;

import com.quocdat.java5.data.dto.request.HocKiDto;
import com.quocdat.java5.data.entity.HocKiE;
import com.quocdat.java5.data.model.HocKiM;
import com.quocdat.java5.repository.HocKyRepo;
import com.quocdat.java5.service.HocKyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HocKyImpl implements HocKyService {

    private final HocKyRepo repo;

    @Override
    public List<HocKiM> getAllHocKy() {
        return HocKiM.convertListHocKyEToListHocKyM(repo.findAll());
    }

    @Override
    public int deleteHocKyByMaHocKy(String maHK) {
        return repo.deleteHocKy(maHK);
    }

    @Override
    public HocKiM saveHocKy(HocKiDto hocky) {
        HocKiE hocKiE = repo.save(hocky);
        return HocKiM.convertHocKyEToHocKyM(hocKiE);
    }
}
