package com.example.command_compte.CommonApi.query.entities;

import com.example.command_compte.CommonApi.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus  status;
    @OneToMany(mappedBy = "account")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Operation> operations;
}
