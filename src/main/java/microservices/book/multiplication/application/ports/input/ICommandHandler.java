package microservices.book.multiplication.application.ports.input;

public interface ICommandHandler<C, R> {
    R handle(C command);
}
