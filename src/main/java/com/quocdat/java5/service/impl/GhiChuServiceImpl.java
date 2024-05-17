package com.quocdat.java5.service.impl;

import com.quocdat.java5.dto.GhiChuDto;
import com.quocdat.java5.mapper.GhiChuMapper;
import com.quocdat.java5.mapper.SinhVienMapper;
import com.quocdat.java5.repository.GhiChuRepo;
import com.quocdat.java5.service.GhiChuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GhiChuServiceImpl implements GhiChuService {
    @Autowired
    private GhiChuRepo repo;
    @Override
    public List<GhiChuDto> getAllGhiChuBySysIdUser(Long sysIdUser) {
        List<GhiChuDto> listGhiChuDto = GhiChuMapper.mapToListGhiChuDto(repo.findAllBySysIdUser(sysIdUser));
        return listGhiChuDto;
    }
}
