package com.example.command_compte.Command.Controller;
import com.example.command_compte.CommonApi.commands.CreateAccountCommand;
import com.example.command_compte.CommonApi.commands.CrediAccountCommand;
import com.example.command_compte.CommonApi.commands.DebitAccountCommand;
import com.example.command_compte.CommonApi.dtos.CreateAccountDto;
import com.example.command_compte.CommonApi.dtos.CreditAccountDto;
import com.example.command_compte.CommonApi.dtos.DebitAccountDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AcocuntController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture createAccount(@RequestBody CreateAccountDto request) {
        return  commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), request.getCurrency(), request.getBalance()));
    }
    @ExceptionHandler
    public String handleException(Exception e) {
        return e.getMessage();
    }
    @GetMapping("/eventStore/{id}")
    public Stream eventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }
    @PutMapping("/credit")
    public CompletableFuture creditAccount(@RequestBody CreditAccountDto request) {
        return  commandGateway.send(new CrediAccountCommand(request.getId(),request.getCurrency(), request.getAmount()));
    }
    @PutMapping("/debit")
    public CompletableFuture debitAccount(@RequestBody DebitAccountDto request) {
        return  commandGateway.send(new DebitAccountCommand(request.getId(),request.getAmount(),request.getCurrency()));
    }
}
