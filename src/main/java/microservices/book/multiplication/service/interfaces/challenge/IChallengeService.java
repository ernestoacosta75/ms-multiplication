package microservices.book.multiplication.service.interfaces.challenge;

import microservices.book.multiplication.domain.ChallengeAttempt;
import microservices.book.multiplication.dtos.challenge.ChallengeAttemptDto;

public interface IChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * @param challengeAttemptDto
     * @return he resulting {@link ChallengeAttempt} object
     */
    ChallengeAttempt verifyAttempt(ChallengeAttemptDto challengeAttemptDto);
}
