package com.quocdat.java5.service.serviceimpl;

import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements com.quocdat.java5.service.Service {
    @Override
    public int deleteAllDisableRecords() {
        System.out.println("Nothing to delete");
        return -1;
    }
}
