package com.example.command_compte.CommonApi.query.entities;
import com.example.command_compte.CommonApi.query.enums.OperationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OperationStatus statut;
    private Double montant;
    private Date date;
    @ManyToOne()
    private Account account;
}
