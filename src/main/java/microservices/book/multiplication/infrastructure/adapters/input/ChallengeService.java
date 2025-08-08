package microservices.book.multiplication.infrastructure.adapters.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.application.mapper.ChallengeMapper;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.challenge.Challenge;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.mapper.ChallengeAttemptEntityMapper;
import microservices.book.multiplication.infrastructure.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeService implements IChallengeService {

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final IChallengeAttemptRepository challengeAttemptRepository;

    @Override
    public ChallengeAttemptResponseDto verifyAttempt(ChallengeAttemptRequestDto challengeAttemptRequestDto) {
        // Checking if the user already exists for that alias, otherwise create it
        UserEntity user = userRepository
                .findByAlias(challengeAttemptRequestDto.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}",
                            challengeAttemptRequestDto.getUserAlias());

                    return userRepository.save(new User(1L, challengeAttemptRequestDto.getUserAlias()));
                });

        // Checking if attempt is correct
        boolean isCorrect = challengeAttemptRequestDto.getGuess() ==
                challengeAttemptRequestDto.getFactorA() * challengeAttemptRequestDto.getFactorB();

        // Building the domain object. Null id for now
        var challengeAttempt = ChallengeMapper.MAPPER.map(challengeAttemptRequestDto);

        ChallengeAttemptAggregate challengeAttemptAggregate =
                ChallengeAttemptAggregate.create(
                        null,
                        UserEntityMapper.MAPPER.map(user),
                        new Challenge(challengeAttemptRequestDto.getFactorA(), challengeAttemptRequestDto.getFactorB()),
                        0,
                        isCorrect
                );

        // Storing the challenge attempt
        var entity = challengeAttemptRepository.save(ChallengeAttemptEntityMapper.MAPPER.map(challengeAttemptAggregate));
        return ChallengeAttemptEntityMapper.MAPPER.map(entity);
    }

    @Override
    public List<ChallengeAttemptResponseDto> getStatsForUser(String userAlias) {
        return ChallengeAttemptEntityMapper.MAPPER.map(
                challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias));
    }
}
