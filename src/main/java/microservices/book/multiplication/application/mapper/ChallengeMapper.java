package microservices.book.multiplication.application.mapper;

import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.domain.model.Challenge;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeMapper {
    public static final ChallengeMapper MAPPER = Mappers.getMapper(ChallengeMapper.class);

    public abstract Challenge map(ChallengeAttemptRequestDto challengeAttemptRequestDto);
}
