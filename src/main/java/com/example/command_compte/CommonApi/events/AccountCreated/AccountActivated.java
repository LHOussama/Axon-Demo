package com.example.command_compte.CommonApi.events.AccountCreated;

import com.example.command_compte.CommonApi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountActivated {
    private String id;
    private AccountStatus   status;;

}
