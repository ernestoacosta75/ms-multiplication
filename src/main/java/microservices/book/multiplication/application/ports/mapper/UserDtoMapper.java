package microservices.book.multiplication.application.ports.mapper;

import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserDtoMapper {

    public static final UserDtoMapper MAPPER = Mappers.getMapper(UserDtoMapper.class);
    public abstract UserDto map(User user);
    public abstract User map(UserDto userDto);
}
