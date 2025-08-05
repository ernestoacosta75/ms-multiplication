package microservices.book.multiplication.application.ports.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptDto;
import microservices.book.multiplication.domain.model.ChallengeAttempt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeAttemptDtoMapper {
    public static final ChallengeAttemptDtoMapper MAPPER = Mappers.getMapper(ChallengeAttemptDtoMapper.class);
    public abstract ChallengeAttempt map(ChallengeAttemptDto challengeRequestDto);
}
