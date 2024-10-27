package com.example.command_compte.CommonApi.events.AccountCreated;

import com.example.command_compte.CommonApi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountCreatedEvenet {
    private String id;
    private String currency;
    private Double balance;
    private AccountStatus   status;
}
