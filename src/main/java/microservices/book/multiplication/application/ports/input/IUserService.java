package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.application.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAllById(final List<Long> ids);
}
