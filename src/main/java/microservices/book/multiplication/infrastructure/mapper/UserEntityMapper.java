package microservices.book.multiplication.infrastructure.mapper;

import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntityMapper MAPPER = Mappers.getMapper(UserEntityMapper.class);

    UserEntity map(UserDto userDto);
    UserDto map(UserEntity userEntity);
}