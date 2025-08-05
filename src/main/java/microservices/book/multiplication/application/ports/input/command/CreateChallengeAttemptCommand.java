package microservices.book.multiplication.application.ports.input.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import microservices.book.multiplication.domain.model.Challenge;
import microservices.book.multiplication.domain.model.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateChallengeAttemptCommand extends BaseCommand<Long>{
    private User user;
    private Challenge challenge;
    private int resultAttempt;
    private boolean correct;

    public CreateChallengeAttemptCommand(Long aggregateId, User user, Challenge challenge, int resultAttempt, boolean correct) {
        super(aggregateId);
        this.user = user;
        this.challenge = challenge;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }
}
