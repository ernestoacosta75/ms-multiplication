package microservices.book.multiplication.application.ports.input.command;

public interface ICommandHandler<C, R> {
    R handle(C command);
}
