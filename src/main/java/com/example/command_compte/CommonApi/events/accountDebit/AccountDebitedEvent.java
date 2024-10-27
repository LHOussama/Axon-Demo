package com.example.command_compte.CommonApi.events.accountDebit;

public class AccountDebitedEvent {
    private String id;
    private Double amount;

    public AccountDebitedEvent(String id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }
}
