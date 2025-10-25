package microservices.book.multiplication.infrastructure.adapters.output;

import microservices.book.multiplication.application.dto.ChallengeSolvedEvent;
import microservices.book.multiplication.application.ports.output.IChallengeEventPubService;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChallengeEventPubService implements IChallengeEventPubService {
    /** AmqpTemplate is a Spring abstraction that simplifies sending and receiving messages to/from an AMQP broker
     * (like RabbitMQ).
     * It defines the AMQP standards.
     */
    private final AmqpTemplate amqpTemplate;
    private final String challengesTopicExchange;

    public ChallengeEventPubService(final AmqpTemplate amqpTemplate,
                                    @Value("${amqp.exchange.attempts}") final String challengesTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    @Override
    public void challengeSolvedEvent(ChallengeAttemptEntity challengeAttemptEntity) {
        ChallengeSolvedEvent event = buildEvent(challengeAttemptEntity);

        // Routing key is 'attempt.correct' or 'attempt.wrong' based on the correctness of the attempt
        String routingKey = "attempt." + (event.isCorrect() ? "correct" : "wrong");
        amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
    }

    private ChallengeSolvedEvent buildEvent(final ChallengeAttemptEntity challengeAttemptEntity) {
        return new ChallengeSolvedEvent(
                challengeAttemptEntity.getId(),
                challengeAttemptEntity.isCorrect(),
                challengeAttemptEntity.getFactorA(),
                challengeAttemptEntity.getFactorB(),
                challengeAttemptEntity.getUser().getId(),
                challengeAttemptEntity.getUser().getAlias());
    }
}
