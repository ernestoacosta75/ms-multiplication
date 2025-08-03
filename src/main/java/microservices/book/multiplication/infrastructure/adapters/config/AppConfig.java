package microservices.book.multiplication.infrastructure.adapters.config;

import lombok.Setter;
import microservices.book.multiplication.application.ports.input.command.IChallengeAttemptCommand;
import microservices.book.multiplication.application.ports.output.persistence.IChallengeAttemptRepository;
import microservices.book.multiplication.application.factory.IChallengeAttemptFactory;
import microservices.book.multiplication.application.factory.impl.ChallengeAttemptFactory;
import microservices.book.multiplication.application.ports.input.query.IChallengeAttemptQuery;
import microservices.book.multiplication.application.query.impl.ChallengeAttemptQuery;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.ChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.IChallengeAttemptJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {

    @Autowired
    private IChallengeAttemptJpaRepository challengeAttemptJpaRepository;

    @Bean
    public IChallengeAttemptCommand challengeAttemptCommand() {
        return new ChallengeAttemptCommand();
    }

    @Bean
    public IChallengeAttemptQuery challengeAttemptQuery() {
        return new ChallengeAttemptQuery();
    }

    @Bean
    public IChallengeAttemptFactory challengeAttemptFactory() {
        return new ChallengeAttemptFactory(challengeAttemptCommand(), challengeAttemptQuery());
    }

    private IChallengeAttemptRepository challengeAttemptRepository() {
        return new ChallengeAttemptRepository(challengeAttemptJpaRepository);
    }
}
