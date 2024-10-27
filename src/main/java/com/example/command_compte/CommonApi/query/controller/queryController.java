package com.example.command_compte.CommonApi.query.controller;
import com.example.command_compte.CommonApi.queris.GetAccountBydId;
import com.example.command_compte.CommonApi.queris.GetAllAccounts;
import com.example.command_compte.CommonApi.query.entities.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@Slf4j
@RestController
@AllArgsConstructor
public class queryController {
    private QueryGateway queryGateway;
    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class)).join();
    } @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable String id){
        return queryGateway.query(new GetAccountBydId(id), ResponseTypes.instanceOf(Account.class)).join();
    }
    @ExceptionHandler
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
