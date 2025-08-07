package microservices.book.multiplication.application.ports.output.event;

import lombok.Data;
import microservices.book.multiplication.domain.model.User;

@Data
public class ChallengeAttemptCreatedEvent {
    private final Long Id;
    private final User user;
    private final int factorA;
    private final int factorB;
    private final int resultAttempt;
    private final int userGuess;
    private final boolean correct;
}
