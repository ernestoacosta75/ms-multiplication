package microservices.book.multiplication.persistence.mapper;

import microservices.book.multiplication.application.domain.model.ChallengeAttempt;
import microservices.book.multiplication.persistence.repository.entity.ChallengeAttemptPersistable;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptPersistableMapper {

    public static final ChallengeAttemptPersistableMapper MAPPER = Mappers.getMapper(ChallengeAttemptPersistableMapper.class);

    public abstract ChallengeAttemptPersistable map(ChallengeAttempt challengeAttempt);

    public abstract ChallengeAttempt map(ChallengeAttemptPersistable challengeAttemptPersistable);
}