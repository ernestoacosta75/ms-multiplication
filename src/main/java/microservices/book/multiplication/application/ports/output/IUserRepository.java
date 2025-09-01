package microservices.book.multiplication.application.ports.output;

import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;

import java.util.Optional;

/**
 * Domain repository for the users.
 */
public interface IUserRepository {
    UserEntity save(UserDto userDto);
    Optional<UserEntity> findByAlias(final String alias);
}
