package microservices.book.multiplication.application.ports.input.mapper;

import microservices.book.multiplication.domain.model.ChallengeAttempt;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeRequestMapper {
    public static final ChallengeRequestMapper MAPPER = Mappers.getMapper(ChallengeRequestMapper.class);
    public abstract ChallengeAttempt map(ChallengeAttemptRequest request);
    public abstract ChallengeAttemptResponse map(ChallengeAttempt challengeAttempt);
}
