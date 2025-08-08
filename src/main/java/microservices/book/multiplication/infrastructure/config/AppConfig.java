package microservices.book.multiplication.infrastructure.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.Setter;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.ChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.UserRepository;
import microservices.book.multiplication.infrastructure.adapters.output.crud.IChallengeAttemptCrudRepository;
import microservices.book.multiplication.infrastructure.adapters.output.crud.IUserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {

    @Autowired
    private IChallengeAttemptCrudRepository challengeAttemptCrudRepository;

    @Autowired
    private IUserCrudRepository userCrudRepository;

    @Bean
    public Hibernate5Module hibernateModule() {
        return new Hibernate5Module();
    }

    private IUserRepository userRepository() {
        return new UserRepository(userCrudRepository);
    }

    private IChallengeAttemptRepository challengeAttemptRepository() {
        return new ChallengeAttemptRepository(challengeAttemptCrudRepository);
    }
}
