package com.quocdat.java5.beanconfig;

import com.quocdat.java5.bean.SchoolInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean("schoolInformationConfig")
    public SchoolInformation SchoolInformationConfigs(){
        SchoolInformation school = new SchoolInformation();
        school.setFullName("FPoly - UpdateName");
        school.setLocation("HO - UpdateLocation");
        return school;
    }

}
