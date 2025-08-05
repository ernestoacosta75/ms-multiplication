package microservices.book.multiplication.application.service.impl;

import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.application.ports.input.command.CreateChallengeAttemptCommand1;
import microservices.book.multiplication.domain.service.ChallengeService;
import microservices.book.multiplication.infrastructure.adapters.config.AppConfig;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Import(AppConfig.class)
class ChallengeServiceTest {

    private IChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeService();
    }

    @Test
    void checkCorrectAttemptTest() {
        // given
        CreateChallengeAttemptCommand1 createChallengeAttemptCommand = CreateChallengeAttemptCommand1.builder()
                .commandName("CreateChallengeAttempt")
                .challengeAttemptRequest(new ChallengeAttemptRequest(50, 60, "john_doe", 3000))
                .build();

        // when
        ChallengeAttemptResponse resultAttempt = challengeService.verifyAttempt(createChallengeAttemptCommand);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        CreateChallengeAttemptCommand1 createChallengeAttemptCommand = CreateChallengeAttemptCommand1.builder()
                .commandName("CreateChallengeAttempt")
                .challengeAttemptRequest(new ChallengeAttemptRequest(50, 60, "john_doe", 5000))
                .build();

        // when
        ChallengeAttemptResponse resultAttempt = challengeService.verifyAttempt(createChallengeAttemptCommand);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }
}