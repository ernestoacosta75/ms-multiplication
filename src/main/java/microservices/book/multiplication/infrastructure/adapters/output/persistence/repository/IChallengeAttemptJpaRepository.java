package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.springframework.data.repository.CrudRepository;

public interface IChallengeAttemptJpaRepository extends CrudRepository<ChallengeAttemptEntity, Long> {
}
