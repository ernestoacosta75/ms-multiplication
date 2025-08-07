package microservices.book.multiplication.infrastructure.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.ports.input.query.FindChallengeAttemptQuery;
import microservices.book.multiplication.application.ports.output.persistence.repository.IChallengeAttemptRepository;
import microservices.book.multiplication.application.ports.output.event.ChallengeAttemptCreatedEvent;
import microservices.book.multiplication.domain.exception.ChallengeAttemptNotFoundException;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.aggregate.ChallengeAttemptAxonAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.mapper.UserEntityMapper;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A projection is a dedicated class that will match the DB operations for every received event.
 * The handlers are defined for every emitted Event.
 *
 * With the @EventHandler annotation we mark a method as a handler for a specific Event emitted.
 *
 * ith the @QueryHandler annotation we mark a method as a handler for a specific Query emitted.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ChallengeAttemptProjection {

    private final IChallengeAttemptRepository challengeAttemptRepository;
    private final QueryUpdateEmitter updateEmitter;

    @EventHandler
    public void on(ChallengeAttemptCreatedEvent event) {
        log.info("Handling a Challenge Attempt creation command {}", event.getId());
        ChallengeAttemptEntity challengeAttemptEntity = new ChallengeAttemptEntity(
                event.getId(),
                UserEntityMapper.MAPPER.map(event.getUser()),
                event.getFactorA(),
                event.getFactorB(),
                event.getResultAttempt(),
                event.isCorrect()
        );

        this.challengeAttemptRepository.saveChallengeAttempt(challengeAttemptEntity);
    }

    @QueryHandler
    public ChallengeAttemptAggregate handle(FindChallengeAttemptQuery query) throws  ChallengeAttemptNotFoundException {
        log.info("Handling FindChallengeAttemptQuery query: {}", query);

        Optional<ChallengeAttemptAggregate> challengeAttemptAggregate = this.challengeAttemptRepository.findById(query.getQueryId());
        if(!challengeAttemptAggregate.isPresent()) {
            throw new ChallengeAttemptNotFoundException(query.getQueryId());
        }

        return challengeAttemptAggregate.get();
    }
}
