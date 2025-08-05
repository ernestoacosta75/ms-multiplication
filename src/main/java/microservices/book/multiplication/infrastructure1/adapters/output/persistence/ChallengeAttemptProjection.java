package microservices.book.multiplication.infrastructure1.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.event.ChallengeAttemptCreatedEvent;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.mapper.UserEntityMapper;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.repository.ChallengeAttemptRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

/**
 * A projection is a dedicated class that will match the DB operations for every received event.
 * The handlers are defined for every emitted Event.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ChallengeAttemptProjection {

    private final ChallengeAttemptRepository challengeAttemptRepository;
    private final QueryUpdateEmitter updateEmitter;

    @EventHandler
    public void on(ChallengeAttemptCreatedEvent event) {
        log.info("Handling a Challenge Attempt creation command {}", event.getId());
        ChallengeAttemptEntity challengeAttemptEntity = new ChallengeAttemptEntity(
                event.getId(),
                UserEntityMapper.MAPPER.map(event.getUser()),
                event.getChallenge().getFactorA(),
                event.getChallenge().getFactorB(),
                event.getResultAttempt(),
                event.isCorrect()
        );

        this.challengeAttemptRepository.saveChallengeAttempt(challengeAttemptEntity);
    }
}
