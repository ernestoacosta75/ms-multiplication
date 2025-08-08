package microservices.book.multiplication.application.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptMapper {
    public static final ChallengeAttemptMapper MAPPER = Mappers.getMapper(ChallengeAttemptMapper.class);

    public abstract ChallengeAttemptResponseDto map(ChallengeAttemptAggregate challengeAttemptAggregate);
    public abstract List<ChallengeAttemptResponseDto> map(List<ChallengeAttemptAggregate> challengeAttemptAggregates);
    // public abstract ChallengeAttemptEntity map(ChallengeAttemptResponseDto challengeAttemptResponseDto);
}
