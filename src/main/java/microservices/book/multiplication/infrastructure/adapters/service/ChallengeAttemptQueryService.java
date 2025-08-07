package microservices.book.multiplication.infrastructure.adapters.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.application.ports.input.query.FindChallengeAttemptQuery;
import microservices.book.multiplication.application.ports.service.IChallengeAttemptQueryService;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ChallengeAttemptQueryService implements IChallengeAttemptQueryService {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    @Override
    public CompletableFuture<ChallengeAttemptAggregate> findById(Long challengeAttemptId) {
        return this.queryGateway.query(
                new FindChallengeAttemptQuery(challengeAttemptId),
                ResponseTypes.instanceOf(ChallengeAttemptAggregate.class)
        );
    }
}
