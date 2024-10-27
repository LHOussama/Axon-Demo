package com.example.command_compte.CommonApi.dtos;

public class CreateAccountDto {
    private String currency;
    private Double balance;

    public CreateAccountDto(String currency, Double balance) {
        this.currency = currency;
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getBalance() {
        return balance;
    }
}
