package microservices.book.multiplication.application.ports.output.persistence.repository;

import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.springframework.stereotype.Repository;

/**
 * Domain repository for the challenge attempts.
 */
@Repository
public interface IChallengeAttemptRepository {
    void saveChallengeAttempt(ChallengeAttemptEntity challengeAttemptEntity);
}
