package com.example.command_compte.CommonApi.query.repositories;

import com.example.command_compte.CommonApi.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

@Repository
public interface AccountRepositori extends JpaRepository<Account,String> {
}
