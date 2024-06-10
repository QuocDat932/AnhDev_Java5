package com.quocdat.java5.service;

import com.quocdat.java5.model.MailModel;

public interface MailService {
    void sendMail(MailModel mailModel) throws Exception;
}
