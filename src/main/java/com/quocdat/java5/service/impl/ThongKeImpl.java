package com.quocdat.java5.service.impl;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.model.ThongKeM;
import com.quocdat.java5.repository.ThongKeRepo;
import com.quocdat.java5.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThongKeImpl  implements ThongKeService {
    private final ThongKeRepo thongKeRepo;
    @Override
    public ThongKeM getPassFailRates(String maMonHoc, String maHK) {
        List<LichSuHocTapE> listLichSuHocTap = thongKeRepo.getPassFailRates(maMonHoc, maHK);
        if (listLichSuHocTap.isEmpty()) {
            return null;
        }
        long soLuongPass = listLichSuHocTap
                .stream()
                .filter(lichSuHocTap -> "Passed".equals(lichSuHocTap.getKetQua()))
                .count();
        long tiLePass = soLuongPass*100/listLichSuHocTap.size();
        long tiLeFail = 100-tiLePass;
        return new ThongKeM().setTiLeFail(tiLeFail).setTiLePass(tiLePass);
    }
}

