package com.example.command_compte.CommonApi.events.accountCredit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountCreditedEvent {
    private String id;
    private Double amount;

}
