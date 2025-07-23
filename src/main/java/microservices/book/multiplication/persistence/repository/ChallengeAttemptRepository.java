package microservices.book.multiplication.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.domain.repository.IChallengeAttemptRepository;
import microservices.book.multiplication.persistence.repository.jpa.IChallengeAttemptJpaRepository;

@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptJpaRepository challengeAttemptJpaRepository;
}
