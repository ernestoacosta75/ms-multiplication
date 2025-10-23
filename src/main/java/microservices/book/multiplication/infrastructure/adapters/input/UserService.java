package microservices.book.multiplication.infrastructure.adapters.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.ports.input.IUserService;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.infrastructure.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserEntityMapper.MAPPER::map)
                .toList();
    }

    @Override
    public List<UserDto> findAllById(List<Long> ids) {
        log.info("Getting users with ids {}", ids);
        var x = userRepository.findAllById(ids).stream()
                .map(UserEntityMapper.MAPPER::map)
                .toList();

        return x;
    }
}
