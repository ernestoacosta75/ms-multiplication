package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;

@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptJpaRepository challengeAttemptJpaRepository;
}
