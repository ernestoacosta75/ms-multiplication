package microservices.book.multiplication.infrastructure.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptEntityMapper {

    public static final ChallengeAttemptEntityMapper MAPPER = Mappers.getMapper(ChallengeAttemptEntityMapper.class);

    @Mapping(target = "factorA", source = "challenge.factorA")
    @Mapping(target = "factorB", source = "challenge.factorB")
    @Mapping(target = "resultAttempt", source = "resultAttempt")
    @Mapping(target = "user", source = "user")
    public abstract ChallengeAttemptEntity map(ChallengeAttemptAggregate challengeAttemptAggregate);
    public abstract ChallengeAttemptResponseDto map(ChallengeAttemptEntity challengeAttemptEntity);
    public abstract List<ChallengeAttemptResponseDto> map(List<ChallengeAttemptEntity> challengeAttemptEntity);

}