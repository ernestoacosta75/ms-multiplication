package microservices.book.multiplication.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.User;
import microservices.book.multiplication.infrastructure.adapters.output.crud.IUserCrudRepository;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final IUserCrudRepository userCrudRepository;

    @Override
    public UserEntity save(User user) {
        var entity = this.userCrudRepository.save(UserEntityMapper.MAPPER.map(user));
        return entity;
    }

    @Override
    public Optional<UserEntity> findByAlias(String alias) {
        return userCrudRepository.findByAlias(alias);
    }
}
