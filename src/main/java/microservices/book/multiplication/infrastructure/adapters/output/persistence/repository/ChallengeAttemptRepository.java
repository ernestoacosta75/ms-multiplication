package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.persistence.repository.IChallengeAttemptRepository;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.mapper.ChallengeAttemptEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptJpaRepository challengeAttemptJpaRepository;

    @Override
    public void saveChallengeAttempt(ChallengeAttemptEntity challengeAttemptEntity) {
        this.challengeAttemptJpaRepository.save(challengeAttemptEntity);
    }

    @Override
    public Optional<ChallengeAttemptAggregate> findById(long challengeAttemptId) {
        return Optional.ofNullable(ChallengeAttemptEntityMapper.MAPPER.map(this.challengeAttemptJpaRepository.findById(challengeAttemptId).get()));
    }
}
