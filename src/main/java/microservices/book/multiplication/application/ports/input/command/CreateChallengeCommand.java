package microservices.book.multiplication.application.ports.input.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateChallengeCommand extends BaseCommand<Long>{
    private int factorA;
    private int factorB;
    private int guess;

    public CreateChallengeCommand(Long aggregateId, int factorA, int factorB, int guess) {
        super(aggregateId);
        this.factorA = factorA;
        this.factorB = factorB;
        this.guess = guess;
    }
}
