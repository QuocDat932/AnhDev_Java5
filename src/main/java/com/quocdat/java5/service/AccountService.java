package com.quocdat.java5.service;

import com.quocdat.java5.entity.AccountE;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountE> getAllAccount();

    Optional<AccountE> getAccountByTkAndMk(String tk, String mk) throws SQLException;
}
