package microservices.book.multiplication.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserRepository userRepository;

    @GetMapping("/{idList}")
    public List<User> getUsers(@PathVariable final List<Long> idList) {
        log.info("Getting users with ids {}", idList);
        return userRepository.findAllById(idList);
    }
}
