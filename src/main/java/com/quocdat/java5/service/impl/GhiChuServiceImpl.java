package com.quocdat.java5.service.impl;

import com.quocdat.java5.dto.GhiChuDto;
import com.quocdat.java5.mapper.GhiChuMapper;
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
    public List<GhiChuDto> getAllGhiChuBySysIdUser(Long sysIdUser, Long month, Long year) {
        List<GhiChuDto> listGhiChuDto = GhiChuMapper.mapToListGhiChuDto(repo.findAllBySysIdUser(sysIdUser, month, year));
        return listGhiChuDto;
    }

    @Override
    public Boolean saveGhiChu(GhiChuDto ghiChuDto) {
        GhiChuDto ghiChuDtoResult = GhiChuMapper.mapToGhiChuDto(repo.save(GhiChuMapper.mapToGhiChu(ghiChuDto)));
        if (!Objects.nonNull(ghiChuDtoResult)) {
            return false;
        }
        return false;
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
