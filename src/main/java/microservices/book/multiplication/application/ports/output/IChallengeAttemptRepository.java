package microservices.book.multiplication.application.ports.output;

import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;

import java.util.List;

/**
 * Domain repository for the challenge attempts.
 */
public interface IChallengeAttemptRepository {
    List<ChallengeAttemptEntity> findTop10ByUserAliasOrderByIdDesc(String userAlias);
    List<ChallengeAttemptEntity> getAllChallenges();
    ChallengeAttemptEntity save(ChallengeAttemptEntity entity);
}
