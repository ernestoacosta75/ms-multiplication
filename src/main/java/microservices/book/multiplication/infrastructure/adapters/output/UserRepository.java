package microservices.book.multiplication.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.crud.IUserCrudRepository;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final IUserCrudRepository userCrudRepository;

    @Override
    public UserEntity save(UserDto userDto) {
        var entity = this.userCrudRepository.save(UserEntityMapper.MAPPER.map(userDto));
        return entity;
    }

    @Override
    public Optional<UserEntity> findByAlias(String alias) {
        return userCrudRepository.findByAlias(alias);
    }

    @Override
    public List<UserEntity> findAllById(List<Long> ids) {
        return userCrudRepository.findAllById(ids);
    }
}
