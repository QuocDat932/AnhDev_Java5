package com.quocdat.java5.service.impl;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.model.ThongKeM;
import com.quocdat.java5.repository.ThongKeRepo;
import com.quocdat.java5.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Object , Integer> result = new HashMap<>();
        result.put(0.0,0);
        result.put(1.0,0);
        result.put(2.0,0);
        result.put(3.0,0);
        result.put(4.0,0);
        result.put(5.0,0);
        result.put(6.0,0);
        result.put(7.0,0);
        result.put(8.0,0);
        result.put(9.0,0);
        result.put(10.0,0);
        listLichSuHocTap.forEach(lichSuHocTap -> {
            result.put(lichSuHocTap.getKetQua() , result.get(lichSuHocTap.getKetQua()) == null ? 1 : result.get(lichSuHocTap.getKetQua()) + 1 );
            result.put((double)Math.round(lichSuHocTap.getDiemThi()),
                    result.get(
                            (double)Math.round(lichSuHocTap.getDiemThi())) + 1 );
        });
        long tiLePass = result.get("Passed") * 100 / listLichSuHocTap.size();
        long tiLeFail = 100-tiLePass;
        return new ThongKeM()
                .setTiLeFail(tiLeFail)
                .setTiLePass(tiLePass)
                .setDiem0(result.get(0.0))
                .setDiem1(result.get(1.0))
                .setDiem2(result.get(2.0))
                .setDiem3(result.get(3.0))
                .setDiem4(result.get(4.0))
                .setDiem5(result.get(5.0))
                .setDiem6(result.get(6.0))
                .setDiem7(result.get(7.0))
                .setDiem8(result.get(8.0))
                .setDiem9(result.get(9.0))
                .setDiem10(result.get(10.0));
    }
}

