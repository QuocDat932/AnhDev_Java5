package com.quocdat.java5.data.model;

import com.quocdat.java5.data.entity.LichSuHocTapE;
import com.quocdat.java5.data.entity.SinhVienE;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class ThongKeM {
   private long tiLePass;
   private long tiLeFail;
   private long diem0;
   private long diem1;
   private long diem2;
   private long diem3;
   private long diem4;
   private long diem5;
   private long diem6;
   private long diem7;
   private long diem8;
   private long diem9;
   private long diem10;

//   public ThongKeM(long tiLePass, long tiLeFail) {
//      this.tiLePass = tiLePass;
//      this.tiLeFail = tiLeFail;
//   }
//   public static ThongKeM convertLichSuHocTapEToThongKeM(LichSuHocTapE lichSuHocTapE){
//      return ThongKeM.builder()
//              .tiLePass(lichSuHocTapE.getTiLePass())
//              .tiLeFail(lichSuHocTapE.getTileFail())
//              .build();
//   }

}
