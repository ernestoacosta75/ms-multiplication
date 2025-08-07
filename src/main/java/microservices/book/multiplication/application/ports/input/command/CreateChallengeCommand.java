package microservices.book.multiplication.application.ports.input.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import microservices.book.multiplication.domain.model.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateChallengeCommand extends BaseCommand<Long>{
    private User user;
    private int factorA;
    private int factorB;
    private int guess;

    public CreateChallengeCommand(Long aggregateId, User user, int factorA, int factorB) {
        super(aggregateId);
        this.user = user;
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
