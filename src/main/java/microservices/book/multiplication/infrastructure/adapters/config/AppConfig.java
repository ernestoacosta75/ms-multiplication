package microservices.book.multiplication.infrastructure.adapters.config;

import lombok.Setter;
import microservices.book.multiplication.application.ports.output.persistence.repository.IChallengeAttemptRepository;
import microservices.book.multiplication.application.ports.output.persistence.repository.IUserRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.ChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.IChallengeAttemptJpaRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.IUserJpaRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {

    @Autowired
    private IChallengeAttemptJpaRepository challengeAttemptJpaRepository;

    @Autowired
    private IUserJpaRepository userJpaRepository;

    private IChallengeAttemptRepository challengeAttemptRepository() {
        return new ChallengeAttemptRepository(challengeAttemptJpaRepository);
    }

    private IUserRepository userRepository() {
        return new UserRepository(userJpaRepository);
    }
}
