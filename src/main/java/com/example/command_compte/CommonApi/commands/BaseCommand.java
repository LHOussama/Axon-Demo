package com.example.command_compte.CommonApi.commands;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
@Getter
public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    private T id;
}
