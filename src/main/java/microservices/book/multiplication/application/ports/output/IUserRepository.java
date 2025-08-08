package microservices.book.multiplication.application.ports.output;

import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;

import java.util.Optional;

/**
 * Domain repository for the users.
 */
public interface IUserRepository {
    UserEntity save(User user);
    Optional<UserEntity> findByAlias(final String alias);
}
