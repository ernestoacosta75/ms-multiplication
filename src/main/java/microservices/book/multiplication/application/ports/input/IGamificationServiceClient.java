package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;

public interface IGamificationServiceClient {
    public boolean sendAttempt(final ChallengeAttemptAggregate challengeAttemptAggregate);
}
