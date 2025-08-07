package microservices.book.multiplication.infrastructure.adapters.input.query.handler;

import microservices.book.multiplication.application.ports.input.IQueryHandler;
import microservices.book.multiplication.application.ports.input.query.FindChallengeAttemptQuery;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;

public class GetChallengeAttemptByIdQueryHandler implements IQueryHandler<FindChallengeAttemptQuery, Long, ChallengeAttemptAggregate> {

    @Override
    public ChallengeAttemptAggregate handle(FindChallengeAttemptQuery query) {
        return null;
    }
}
