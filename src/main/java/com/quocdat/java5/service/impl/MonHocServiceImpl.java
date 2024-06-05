package com.quocdat.java5.service.impl;

import com.quocdat.java5.convert.MonHocConvert;
import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;
import com.quocdat.java5.repository.MonHocRepo;
import com.quocdat.java5.service.MonHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonHocServiceImpl implements MonHocService {
    private final MonHocRepo monHocRepo;

    @Override
    public List<MonHocE> getAllMonHoc() {
        return monHocRepo.findAll();
    }

    @Override
    public MonHocE SaveMonHoc(MonHocDto monHoc){
        MonHocE monHocE = MonHocConvert.convertMonHocDtoToMonHocE(monHoc);

        return monHocRepo.save(monHocE);
    }

    @Override
    public String deleteByMaMonHoc(String maMonHoc) {
        int result = monHocRepo.deleteByMaMonHoc(maMonHoc);

        if (result > 0) {
            return "Xóa thành công môn học với mã: " + maMonHoc;
        } else {
            return "Không tìm thấy mã môn học: " + maMonHoc;
        }
    }

    @Override
    public List<MonHocE> getMonHocByMaMonHoc(String maMonHoc) {
        return monHocRepo.getMonHocByMaMonHoc(maMonHoc +"%");
    }
}
