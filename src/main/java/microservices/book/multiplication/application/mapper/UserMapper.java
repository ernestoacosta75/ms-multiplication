package microservices.book.multiplication.application.mapper;

import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    public static final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    public abstract User map(UserEntity userEntity);
}
