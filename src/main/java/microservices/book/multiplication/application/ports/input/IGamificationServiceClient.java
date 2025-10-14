package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;

public interface IGamificationServiceClient {
    public boolean sendAttempt(final ChallengeAttemptEntity challengeAttemptEntity);
}
