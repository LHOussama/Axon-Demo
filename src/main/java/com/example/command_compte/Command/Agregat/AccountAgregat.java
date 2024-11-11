package com.example.command_compte.Command.Agregat;
import com.example.command_compte.CommonApi.commands.CreateAccountCommand;
import com.example.command_compte.CommonApi.commands.CrediAccountCommand;
import com.example.command_compte.CommonApi.commands.DebitAccountCommand;
import com.example.command_compte.CommonApi.enums.AccountStatus;
import com.example.command_compte.CommonApi.events.AccountCreated.AccountActivated;
import com.example.command_compte.CommonApi.events.AccountCreated.AccountCreatedEvenet;
import com.example.command_compte.CommonApi.events.accountCredit.AccountCreditedEvent;
import com.example.command_compte.CommonApi.events.accountDebit.AccountDebitedEvent;
import com.example.command_compte.CommonApi.exceptions.CreateAccountException;
import com.example.command_compte.CommonApi.exceptions.CreditAccountException;
import com.example.command_compte.CommonApi.exceptions.DebitAccountException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
@Aggregate
public class AccountAgregat {
    @AggregateIdentifier
    private String id;
    private String currency;
    private double balance;
    private AccountStatus status;

    @CommandHandler
    public AccountAgregat(CreateAccountCommand command) {
        if(command.getBalance()<0){
            new CreateAccountException("Balance cannot be negative");
        }
        else{
            AggregateLifecycle.apply(new AccountCreatedEvenet(command.getId(),command.getCurrency(),command.getBalance(),AccountStatus.CREATED));
        }
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvenet event){
        this.id = event.getId();
        this.currency= event.getCurrency();
        this.balance = event.getBalance();
        this.status = event.getStatus();
        AggregateLifecycle.apply(new AccountActivated(event.getId(),AccountStatus.ACTIVATED));
    }
    @EventSourcingHandler
    public void on(AccountActivated event){
        this.status = event.getStatus();
    }
    @CommandHandler
    public void credit(CrediAccountCommand command){
        if(command.getAmount()<0){
            throw new CreditAccountException("Amount cannot be negative");
        }
        else{
            AggregateLifecycle.apply(new AccountCreditedEvent(command.getId(),command.getAmount()));
        }
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void debit(DebitAccountCommand command){
        if(command.getAmount()<0){
            throw new DebitAccountException("Amount cannot be negative");
        }
        if(this.balance<command.getAmount()){
            throw new DebitAccountException("Insufficient balance");
        }
        else{
            AggregateLifecycle.apply(new AccountDebitedEvent(command.getId(),command.getAmount()));
        }
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance -= event.getAmount();
    }
}
