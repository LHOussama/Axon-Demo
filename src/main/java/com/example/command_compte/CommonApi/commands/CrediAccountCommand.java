package com.example.command_compte.CommonApi.commands;

import lombok.Getter;

@Getter
public class CrediAccountCommand  extends BaseCommand<String> {
    private Double amount;
    private String currency;

    public CrediAccountCommand(String id,  String currency,Double amount) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
