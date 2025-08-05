package microservices.book.multiplication.application1.ports.input.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptDto;
import microservices.book.multiplication.domain.model.ChallengeAttempt;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeRequestMapper1 {
    public static final ChallengeRequestMapper1 MAPPER = Mappers.getMapper(ChallengeRequestMapper1.class);
    public abstract ChallengeAttempt map(ChallengeAttemptRequest request);
    public abstract ChallengeAttemptResponse map(ChallengeAttempt challengeAttempt);

    //public abstract ChallengeAttempt map(ChallengeAttemptDto challengeAttemptDto);
}
