package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;

import java.util.List;

public interface IChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * @param challengeAttemptRequestDto
     * @return the resulting {@link ChallengeAttemptResponseDto} object
     */
    ChallengeAttemptResponseDto verifyAttempt(ChallengeAttemptRequestDto challengeAttemptRequestDto);

    /**
     *
     * @param userAlias the user's alias
     * @return a list of the last 10 {@link ChallengeAttemptResponseDto}
     */
    List<ChallengeAttemptResponseDto> getStatsForUser(String userAlias);

    List<ChallengeAttemptResponseDto> getAllAttempts();
}
