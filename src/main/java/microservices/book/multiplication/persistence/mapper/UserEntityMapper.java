package microservices.book.multiplication.persistence.mapper;

import microservices.book.multiplication.application.domain.model.User;
import microservices.book.multiplication.persistence.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserEntityMapper {
    public static final UserEntityMapper MAPPER = Mappers.getMapper(UserEntityMapper.class);
    public abstract UserEntity map(User user);
    public abstract User map(UserEntity userEntity);
}
