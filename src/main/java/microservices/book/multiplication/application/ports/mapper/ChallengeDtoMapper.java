package microservices.book.multiplication.application.ports.mapper;

import microservices.book.multiplication.application.dto.ChallengeDto;
import microservices.book.multiplication.domain.model.Challenge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChallengeDtoMapper {

     public static final ChallengeDtoMapper MAPPER = Mappers.getMapper(ChallengeDtoMapper.class);

     @Mapping(target = "userAlias", ignore = true)
     public abstract ChallengeDto map(Challenge challenge);

     public abstract Challenge map(ChallengeDto challengeDto);
}
