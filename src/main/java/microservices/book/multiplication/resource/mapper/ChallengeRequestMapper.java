package microservices.book.multiplication.resource.mapper;

import microservices.book.multiplication.application.domain.model.ChallengeAttempt;
import microservices.book.multiplication.resource.model.ChallengeAttemptRequest;
import microservices.book.multiplication.resource.model.GetChallengeAttemptResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeRequestMapper {
    public static final ChallengeRequestMapper MAPPER = Mappers.getMapper(ChallengeRequestMapper.class);
    public abstract ChallengeAttempt map(ChallengeAttemptRequest request);
    public abstract GetChallengeAttemptResponse map(ChallengeAttempt challengeAttempt);
}
