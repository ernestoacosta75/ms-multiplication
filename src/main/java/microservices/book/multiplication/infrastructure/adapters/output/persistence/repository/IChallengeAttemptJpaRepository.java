package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChallengeAttemptJpaRepository extends JpaRepository<ChallengeAttemptEntity, Long> {
}
