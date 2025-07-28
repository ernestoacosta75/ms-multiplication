package microservices.book.multiplication.persistence.repository.jpa;

import microservices.book.multiplication.persistence.repository.entity.ChallengeAttemptPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IChallengeAttemptJpaRepository extends JpaRepository<ChallengeAttemptPersistable, Long> {
}
