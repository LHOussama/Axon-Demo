package com.example.command_compte.CommonApi.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CreditAccountDto {
    private String id;
    private Double amount;
    private String currency;
}
