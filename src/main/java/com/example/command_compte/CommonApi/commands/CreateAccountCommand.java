package com.example.command_compte.CommonApi.commands;

import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand<String> {
    private String currency;
    private Double balance;

    public CreateAccountCommand(String id, String currency, Double balance) {
        super(id);
        this.currency = currency;
        this.balance = balance;
    }
}
