package microservices.book.multiplication.service.implementations.challenge;

import microservices.book.multiplication.domain.ChallengeAttempt;
import microservices.book.multiplication.dtos.challenge.ChallengeAttemptDto;
import microservices.book.multiplication.service.interfaces.challenge.IChallengeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class ChallengeServiceTest {

    private IChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeService();
    }

    @Test
    void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptDto attemptDto =
                new ChallengeAttemptDto(50, 60, "john_doe", 3000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        ChallengeAttemptDto attemptDto =
                new ChallengeAttemptDto(50, 60, "john_doe", 5000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }
}