package microservices.book.multiplication.infrastructure.adapters.input;

import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.mapper.UserMapper;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.application.ports.input.IGamificationServiceClient;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;
import microservices.book.multiplication.application.ports.output.IUserRepository;
import microservices.book.multiplication.domain.model.challenge.Challenge;
import microservices.book.multiplication.domain.model.challenge.ChallengeAttemptAggregate;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.config.AppConfig;
import microservices.book.multiplication.infrastructure.mapper.ChallengeAttemptEntityMapper;
import microservices.book.multiplication.infrastructure.mapper.UserEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import(AppConfig.class)
class ChallengeServiceTest {

    private IChallengeService challengeService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IChallengeAttemptRepository challengeAttemptRepository;

    @Mock
    private IGamificationServiceClient gamificationServiceClient;

    @BeforeEach
    void setUp() {
        challengeService = new ChallengeService(userRepository, challengeAttemptRepository, gamificationServiceClient);
        given(challengeAttemptRepository.save(any()))
                .will(returnsFirstArg());
    }

    @Test
    void checkCorrectAttemptTest() {
        // given
        ChallengeAttemptRequestDto attemptRequest = new ChallengeAttemptRequestDto(50, 60, "john_doe", 3000);
        UserEntity savedUserEntity = new UserEntity(1L, "john_doe");
        var userDto = UserDto.builder().alias("john_doe").build();

        // when
        when(userRepository.findByAlias("john_doe")).thenReturn(Optional.empty());
        when(userRepository.save(any(UserDto.class))).thenReturn(savedUserEntity);
        ChallengeAttemptResponseDto resultAttempt = challengeService.verifyAttempt(attemptRequest);

        var aggregate = ChallengeAttemptAggregate.create(
                resultAttempt.getId(),
                resultAttempt.getUser(),
                new Challenge(resultAttempt.getFactorA(), resultAttempt.getFactorB()),
                resultAttempt.getResultAttempt(),
                resultAttempt.isCorrect());
        // then
        then(resultAttempt.isCorrect()).isTrue();
        verify(userRepository).save(any(UserDto.class)); //new User(1L, "john_doe")
        verify(challengeAttemptRepository)
                .save(ChallengeAttemptEntityMapper.MAPPER.map(aggregate));
        verify(gamificationServiceClient).sendAttempt(aggregate);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        UserEntity existingUserEntity = new UserEntity(1L, "john_doe");

        given(userRepository.findByAlias("john_doe"))
                .willReturn(Optional.of(existingUserEntity));

        ChallengeAttemptRequestDto attemptRequest = new ChallengeAttemptRequestDto(50, 60, "john_doe", 5000);

        // when
        ChallengeAttemptResponseDto resultAttempt = challengeService.verifyAttempt(attemptRequest);

        var aggregate = ChallengeAttemptAggregate.create(
                resultAttempt.getId(),
                UserMapper.MAPPER.map(existingUserEntity),
                new Challenge(resultAttempt.getFactorA(), resultAttempt.getFactorB()),
                resultAttempt.getResultAttempt(),
                resultAttempt.isCorrect());

        // then
        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getUser()).isEqualTo(UserMapper.MAPPER.map(existingUserEntity));
        verify(userRepository, never()).save(any());
        verify(challengeAttemptRepository).save(ChallengeAttemptEntityMapper.MAPPER.map(aggregate));
    }

    @Test
    void checkWrongAttemptTest() {
        // given
        ChallengeAttemptRequestDto attemptRequest =
                new ChallengeAttemptRequestDto(50, 60, "john_doe", 5000);

        UserEntity savedUserEntity = new UserEntity(1L, "john_doe");

        // when
        when(userRepository.findByAlias("john_doe")).thenReturn(Optional.empty());
        when(userRepository.save(any(UserDto.class))).thenReturn(savedUserEntity);
        ChallengeAttemptResponseDto resultAttempt = challengeService.verifyAttempt(attemptRequest);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }

    @Test
    public void retrieveStatsTest() {
        // given
        ChallengeAttemptRequestDto attemptRequest =
                new ChallengeAttemptRequestDto(50, 60, "john_doe", 5000);

        UserEntity userEntity = new UserEntity(1L, "john_doe");

        // when
        ChallengeAttemptEntity challengeAttemptEntity1 = new ChallengeAttemptEntity(1L, userEntity, 50, 60, 3010, false);
        ChallengeAttemptEntity challengeAttemptEntity2 = new ChallengeAttemptEntity(2L, userEntity, 50, 60, 3051, false);

        List<ChallengeAttemptEntity> lastAttemptsEntities = List.of(challengeAttemptEntity1, challengeAttemptEntity2);
        List<ChallengeAttemptResponseDto> latestAttemptsResult = ChallengeAttemptEntityMapper.MAPPER.map(lastAttemptsEntities);

        when(challengeAttemptRepository
                .findTop10ByUserAliasOrderByIdDesc(userEntity.getAlias()))
                .thenReturn(lastAttemptsEntities);

        var x = challengeService.getStatsForUser(userEntity.getAlias());


        // then
        then(latestAttemptsResult).isEqualTo(latestAttemptsResult);
    }
}