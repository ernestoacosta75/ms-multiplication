package microservices.book.multiplication.infrastructure.adapters.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.mapper.UserMapper;
import microservices.book.multiplication.application.ports.input.IGamificationServiceClient;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.challenge.Challenge;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.mapper.ChallengeAttemptEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChallengeService implements IChallengeService {

    private final IUserRepository userRepository;
    private final IChallengeAttemptRepository challengeAttemptRepository;
    private final IGamificationServiceClient gamificationServiceClient;

    @Override
    public ChallengeAttemptResponseDto verifyAttempt(ChallengeAttemptRequestDto request) {

        UserEntity user = userRepository
                .findByAlias(request.getUserAlias())
                .orElseGet(() -> createUser(request.getUserAlias()));

        boolean isCorrect = request.getGuess() ==
                request.getFactorA() * request.getFactorB();

        ChallengeAttemptAggregate challengeAttemptAggregate =
                ChallengeAttemptAggregate.create(
                        null,
                        UserMapper.MAPPER.map(user),
                        new Challenge(request.getFactorA(), request.getFactorB()),
                        request.getGuess(),
                        isCorrect
                );

        var entity = challengeAttemptRepository.save(ChallengeAttemptEntityMapper.MAPPER.map(challengeAttemptAggregate));

        // Sending the attempt to the Gamification microservice
        gamificationServiceClient.sendAttempt(entity);

        return ChallengeAttemptEntityMapper.MAPPER.map(entity);
    }

    @Override
    public List<ChallengeAttemptResponseDto> getStatsForUser(String userAlias) {
        return ChallengeAttemptEntityMapper.MAPPER.map(
                challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias));
    }

    private UserEntity createUser(String userAlias) {
        log.info("Creating new user with alias {}", userAlias);
        var userDto = UserDto.builder().alias(userAlias).build();
        return userRepository.save(userDto);
    }
}
