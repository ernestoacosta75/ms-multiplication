package microservices.book.multiplication.infrastructure.adapters.output.crud;

import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IUserCrudRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByAlias(final String alias);
    List<UserEntity> findAllById(Iterable<Long> ids);
}
