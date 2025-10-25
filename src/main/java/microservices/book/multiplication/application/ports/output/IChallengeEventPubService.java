package microservices.book.multiplication.application.ports.output;

import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;

/**
 * This interface is the contract to communicate with the message broker.
 */
public interface IChallengeEventPubService {
    void challengeSolvedEvent(final ChallengeAttemptEntity challengeAttemptEntity);
}
