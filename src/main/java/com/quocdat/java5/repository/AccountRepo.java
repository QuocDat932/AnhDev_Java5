package com.quocdat.java5.repository;

import com.quocdat.java5.entity.AccountE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<AccountE, Integer> {

    @Query(value = "SELECT sys_id, user_name, tk, mk, role_id FROM sys_user WHERE tk = ?1 AND mk = ?2", nativeQuery = true)
    AccountE getAccountEByTkAndMk(String tk, String mk);

    Optional<AccountE> findAccountByTk(String tk);
}
