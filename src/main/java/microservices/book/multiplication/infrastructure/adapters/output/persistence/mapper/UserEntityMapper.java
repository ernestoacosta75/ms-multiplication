package microservices.book.multiplication.infrastructure.adapters.output.persistence.mapper;

import microservices.book.multiplication.domain.model.User;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserEntityMapper {
    public static final UserEntityMapper MAPPER = Mappers.getMapper(UserEntityMapper.class);
    public abstract UserEntity map(User user);
    public abstract User map(UserEntity userEntity);
}
