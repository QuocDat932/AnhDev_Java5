package com.quocdat.java5.service.impl;

import com.quocdat.java5.entity.AccountE;
import com.quocdat.java5.repository.AccountRepo;
import com.quocdat.java5.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServicesImpl implements AccountService {
    final AccountRepo repo;

    @Override
    public List<AccountE> getAllAccount(){
        return repo.findAll();
    };

    @Override
    public Optional<AccountE> getAccountByTkAndMk(String tk, String mk) throws SQLException{
        var result = repo.getAccountEByTkAndMk(tk, mk);
        return Optional.ofNullable(result);
    };

}
