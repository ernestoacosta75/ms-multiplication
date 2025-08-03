package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserJpaRepository extends CrudRepository<UserEntity, Long> {
}
