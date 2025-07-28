package microservices.book.multiplication.application.service.impl;

import microservices.book.multiplication.application.factory.IChallengeAttemptFactory;
import microservices.book.multiplication.application.service.IChallengeService;
import microservices.book.multiplication.common.config.AppConfig;
import microservices.book.multiplication.resource.model.ChallengeAttemptRequest;
import microservices.book.multiplication.resource.model.ChallengeAttemptResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Import(AppConfig.class)
class ChallengeServiceTest {

    private IChallengeService challengeService;

    @Mock
    private IChallengeAttemptFactory challengeAttemptFactory;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeService(challengeAttemptFactory);
    }

    @Test
    void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptRequest attemptRequest = new ChallengeAttemptRequest(50, 60, "john_doe", 3000);

        // when
        ChallengeAttemptResponse resultAttempt = challengeService.verifyAttempt(attemptRequest);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        ChallengeAttemptRequest attemptRequest =
                new ChallengeAttemptRequest(50, 60, "john_doe", 5000);

        // when
        ChallengeAttemptResponse resultAttempt = challengeService.verifyAttempt(attemptRequest);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }
}