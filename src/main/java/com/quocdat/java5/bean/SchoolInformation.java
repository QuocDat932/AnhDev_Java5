package com.quocdat.java5.bean;

import org.springframework.stereotype.Component;

@Component
public class SchoolInformation {
    private String fullName = "Fpt Polytechnic";
    private String location = "Tp HCM";

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
