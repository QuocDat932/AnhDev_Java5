package com.quocdat.java5.bean.impl;

import com.quocdat.java5.bean.SchoolInformationMethod;
import org.springframework.stereotype.Component;

@Component
public class SchoolInformationMethodImpl implements SchoolInformationMethod {

    @Override
    public String thuHocPhi(){
        return "Thu Học Phí Định Kì";
    }
}
