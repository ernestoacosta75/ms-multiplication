package microservices.book.multiplication.application.ports.output.persistence.repository;

import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Domain repository for the challenge attempts.
 */
@Repository
public interface IChallengeAttemptRepository {
    void saveChallengeAttempt(ChallengeAttemptEntity challengeAttemptEntity);
    Optional<ChallengeAttemptAggregate> findById(long challengeAttemptId);
}
