package microservices.book.multiplication.persistence.repository.jpa;

import microservices.book.multiplication.persistence.repository.entity.ChallengeAttemptPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChallengeAttemptJpaRepository extends JpaRepository<ChallengeAttemptPersistable, Long> {
}
