package com.quocdat.java5.service.impl;

import com.quocdat.java5.convert.GhiChuConvert;
import com.quocdat.java5.data.dto.GhiChuDto;
import com.quocdat.java5.data.model.GhiChuM;
import com.quocdat.java5.repository.GhiChuRepo;
import com.quocdat.java5.service.GhiChuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GhiChuServiceImpl implements GhiChuService {
    @Autowired
    private GhiChuRepo repo;

    @Override
    public List<GhiChuM> getAllGhiChuBySysIdUser(Long sysIdUser, Long month, Long year) {
        List<GhiChuM> listGhiChuM = GhiChuM.convertListGhiChuEToListGhiChuM(repo.findAllBySysIdUser(sysIdUser, month, year));
        return listGhiChuM;
    }

    @Override
    public Boolean saveGhiChu(GhiChuDto ghiChuDto) {
        GhiChuM ghiChuResult =GhiChuM.convertGhiChuEToGhiChuM(repo.save(GhiChuConvert.convertGhiChuDtoToGhiChuE(ghiChuDto)));
        System.out.println(ghiChuResult);
        if (!Objects.nonNull(ghiChuResult)) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteGhiChuBySysId(Long sysId) {
        try {
            repo.deleteById(sysId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
