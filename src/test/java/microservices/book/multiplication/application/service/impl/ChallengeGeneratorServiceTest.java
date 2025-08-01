package microservices.book.multiplication.application.service.impl;

import microservices.book.multiplication.application.domain.model.Challenge;
import microservices.book.multiplication.application.service.IChallengeGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ChallengeGeneratorServiceTest {

    private IChallengeGeneratorService challengeGeneratorService;

    @Spy
    private Random random;

    @BeforeEach
    void setUp() {
        challengeGeneratorService = new ChallengeGeneratorService(random);
    }

    @Test
    void generateRandomFactorIsBetweenExpectedLimits() {
        //given: 89 is max -min range
        given(random.nextInt(89)).willReturn(20, 30);

        //when: we generate a challenge
        Challenge challenge = challengeGeneratorService.randomChallenge();

        //then: the challenge contains factors as expected
        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}