package microservices.book.multiplication.application.ports.input.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    private final T aggregateId;

    public BaseCommand() {
        this.aggregateId = null;
    }

    public BaseCommand(T aggregateId) {
        this.aggregateId = aggregateId;
    }

    public T getAggregateId() {
        return aggregateId;
    }
}
