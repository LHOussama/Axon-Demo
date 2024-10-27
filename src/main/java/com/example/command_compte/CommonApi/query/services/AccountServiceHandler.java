package com.example.command_compte.CommonApi.query.services;
import com.example.command_compte.CommonApi.events.AccountCreated.AccountActivated;
import com.example.command_compte.CommonApi.events.AccountCreated.AccountCreatedEvenet;
import com.example.command_compte.CommonApi.events.accountCredit.AccountCreditedEvent;
import com.example.command_compte.CommonApi.events.accountDebit.AccountDebitedEvent;
import com.example.command_compte.CommonApi.queris.GetAccountBydId;
import com.example.command_compte.CommonApi.queris.GetAllAccounts;
import com.example.command_compte.CommonApi.query.entities.Account;
import com.example.command_compte.CommonApi.query.entities.Operation;
import com.example.command_compte.CommonApi.query.enums.OperationStatus;
import com.example.command_compte.CommonApi.query.repositories.AccountRepositori;
import com.example.command_compte.CommonApi.query.repositories.OperationRepositori;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceHandler {
    private final AccountRepositori accountRepository;
    private OperationRepositori operationRepositori;
    @EventHandler
    public void on(AccountCreatedEvenet event) {
        log.info("****************************");
        log.info("Handling AccountCreatedEvenet: " + event.getId());
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivated event) {
        log.info("****************************");
        log.info("Handling AccountActivated: " + event.getId());
        Account account = accountRepository.findById(event.getId()).orElseGet(null);
        System.out.println(account.getStatus());
        account.setStatus(event.getStatus());
        System.out.println(account.getStatus());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountCreditedEvent event) {
        log.info("****************************");
        log.info("Handling CreditEvent: " + event.getId());
        Account account = accountRepository.findById(event.getId()).orElseGet(null);
        System.out.println(account.getBalance());
        Operation operation = new Operation();
        operation.setMontant(event.getAmount());
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setStatut(OperationStatus.CREDIT);
        operation.setAccount(account);
        operationRepositori.save(operation);
        account.setBalance(account.getBalance() + event.getAmount());
        System.out.println(account.getBalance());
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountDebitedEvent event) {
        log.info("****************************");
        log.info("Handling CreditEvent: " + event.getId());
        Account account = accountRepository.findById(event.getId()).orElseGet(null);
        System.out.println(account.getBalance());
        Operation operation = new Operation();
        operation.setMontant(event.getAmount());
        operation.setAccount(account);
        operation.setDate(new Date());
        operation.setStatut(OperationStatus.CREDIT);
        operation.setAccount(account);
        operationRepositori.save(operation);
        account.setBalance(account.getBalance() - event.getAmount());
        System.out.println(account.getBalance());
        accountRepository.save(account);
    }
    @QueryHandler
    public List<Account> on(GetAllAccounts query) {
        return accountRepository.findAll();
    }@QueryHandler
    public Account on(GetAccountBydId query) {
        return accountRepository.getById(query.getId());
    }

}
