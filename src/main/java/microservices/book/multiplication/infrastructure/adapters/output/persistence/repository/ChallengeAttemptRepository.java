package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.persistence.repository.IChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptJpaRepository challengeAttemptJpaRepository;

    @Override
    public void saveChallengeAttempt(ChallengeAttemptEntity challengeAttemptEntity) {
        this.challengeAttemptJpaRepository.save(challengeAttemptEntity);
    }
}
