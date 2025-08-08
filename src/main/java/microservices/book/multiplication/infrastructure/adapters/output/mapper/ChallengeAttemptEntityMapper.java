package microservices.book.multiplication.infrastructure.adapters.output.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptEntityMapper {
    public static final ChallengeAttemptEntityMapper MAPPER = Mappers.getMapper(ChallengeAttemptEntityMapper.class);
    public abstract ChallengeAttemptEntity map(ChallengeAttemptAggregate challengeAttemptAggregate);
    public abstract List<ChallengeAttemptResponseDto> map(List<ChallengeAttemptEntity> challengeAttemptEntities);
}
