package microservices.book.multiplication.infrastructure.adapters.output;

import microservices.book.multiplication.application.dto.ChallengeSolvedEvent;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChallengeEventPubServiceTest {

    private ChallengeEventPubService challengeEventPubService;

    @Mock
    private AmqpTemplate amqpTemplate;

    @BeforeEach
    void setUp() {
        challengeEventPubService = new ChallengeEventPubService(amqpTemplate, "test.topic");
    }

    /**
     * In this test, we use Mockito's ArgumentCaptor class to capture the arguments
     * passed to the mock, so we can assert these values later.
     * @param isCorrect
     */
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void sendAttempt(boolean isCorrect) {
        //given
        ChallengeAttemptEntity attemptEntity = createTestAttemptEntity(isCorrect);

        //when
        challengeEventPubService.challengeSolved(attemptEntity);

        //then
        var exchangeCaptor = ArgumentCaptor.forClass(String.class);
        var routingKeyCaptor = ArgumentCaptor.forClass(String.class);
        var eventCaptor = ArgumentCaptor.forClass(ChallengeSolvedEvent.class);

        verify(amqpTemplate).convertAndSend(
                exchangeCaptor.capture(),
                routingKeyCaptor.capture(),
                eventCaptor.capture());

        then(exchangeCaptor.getValue()).isEqualTo("test.topic");
        then(routingKeyCaptor.getValue()).isEqualTo("attempt." + (isCorrect ? "correct" : "wrong"));
        then(eventCaptor.getValue()).isEqualTo(solvedEvent(isCorrect));

    }

    private ChallengeAttemptEntity createTestAttemptEntity(boolean isCorrect) {
        return new ChallengeAttemptEntity(1L, new UserEntity(10L, "john"), 30, 40,
                isCorrect ? 1200 : 1300 , isCorrect);
    }

    private ChallengeSolvedEvent solvedEvent(boolean isCorrect) {
        return new ChallengeSolvedEvent(1L, isCorrect, 30, 40, 10L, "john");
    }

    @AfterEach
    void tearDown() {
    }
}