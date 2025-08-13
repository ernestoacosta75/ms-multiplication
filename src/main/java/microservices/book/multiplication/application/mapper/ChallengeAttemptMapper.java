package microservices.book.multiplication.application.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChallengeAttemptMapper {
    ChallengeAttemptMapper MAPPER = Mappers.getMapper(ChallengeAttemptMapper.class);

    ChallengeAttemptResponseDto map(ChallengeAttemptAggregate challengeAttemptAggregate);
    List<ChallengeAttemptResponseDto> map(List<ChallengeAttemptAggregate> challengeAttemptAggregates);
}