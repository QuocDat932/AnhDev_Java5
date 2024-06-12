package com.quocdat.java5.service.impl;

import com.quocdat.java5.convert.HocKiConvert;
import com.quocdat.java5.convert.MonHocConvert;
import com.quocdat.java5.data.dto.request.MonHocDto;
import com.quocdat.java5.data.entity.MonHocE;
import com.quocdat.java5.data.model.HocKiM;
import com.quocdat.java5.data.model.MonHocM;
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
    public List<MonHocM> getAllMonHoc() {
        return MonHocM.convertListMonHocToListMonHocM(monHocRepo.findAll());
    }

    @Override
    public MonHocM SaveMonHoc(MonHocDto monHoc){
        if(!existsMonHocByMaMonHoc(monHoc.getMaMonHoc())){
            return MonHocM.convertMonHoctoMonHocM(
                    monHocRepo.save(MonHocConvert.convertMonHocDtoToMonHocE(monHoc))
            );
        }else {
            return null;
        }
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
    public List<MonHocM> getMonHocByMaMonHoc(String maMonHoc) {
        return MonHocM.convertListMonHocToListMonHocM(monHocRepo.getMonHocByMaMonHoc(maMonHoc +"%"));
    }

    @Override
    public boolean existsMonHocByMaMonHoc(String maMonHoc) {
        return monHocRepo.existsMonHocByMaMonHoc(maMonHoc);
    }

    @Override
    public MonHocM updateMonHoc(MonHocDto monHoc) {
        if(existsMonHocByMaMonHoc(monHoc.getMaMonHoc())){
            return MonHocM.convertMonHoctoMonHocM(
                    monHocRepo.save(MonHocConvert.convertMonHocDtoToMonHocE(monHoc))
            );
        }else {
            return null;
        }
    }
}
