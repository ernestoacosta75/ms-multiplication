package microservices.book.multiplication.infrastructure.adapters.output.persistence.mapper;

import microservices.book.multiplication.domain.model.ChallengeAttempt;
import microservices.book.multiplication.infrastructure.adapters.output.persistence.entity.ChallengeAttemptEntity;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptEntityMapper {

    public static final ChallengeAttemptEntityMapper MAPPER = Mappers.getMapper(ChallengeAttemptEntityMapper.class);

    public abstract ChallengeAttemptEntity map(ChallengeAttempt challengeAttempt);

    public abstract ChallengeAttempt map(ChallengeAttemptEntity challengeAttemptEntity);
}