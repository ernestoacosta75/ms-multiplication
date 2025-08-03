package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.persistence.IChallengeAttemptRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptJpaRepository challengeAttemptJpaRepository;
}
