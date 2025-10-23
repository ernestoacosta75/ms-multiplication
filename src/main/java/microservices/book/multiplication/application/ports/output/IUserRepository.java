package microservices.book.multiplication.application.ports.output;

import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository for the users.
 */
public interface IUserRepository {
    UserEntity save(UserDto userDto);
    Optional<UserEntity> findByAlias(final String alias);
    List<UserEntity> findAll();
    List<UserEntity> findAllById(final List<Long> ids);
}
