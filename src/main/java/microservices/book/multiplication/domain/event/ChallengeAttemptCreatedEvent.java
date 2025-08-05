package microservices.book.multiplication.domain.event;

import lombok.Data;
import microservices.book.multiplication.domain.model.Challenge;
import microservices.book.multiplication.domain.model.User;

@Data
public class ChallengeAttemptCreatedEvent {
    private final Long Id;
    private final User user;
    private final Challenge challenge;
    private final int resultAttempt;
    private final boolean correct;
}
