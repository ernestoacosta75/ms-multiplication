package microservices.book.multiplication.application.service;

import microservices.book.multiplication.resource.model.ChallengeAttemptRequest;
import microservices.book.multiplication.resource.model.ChallengeAttemptResponse;

public interface IChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * @param challengeAttemptRequest
     * @return the resulting {@link ChallengeAttemptResponse} object
     */
    ChallengeAttemptResponse verifyAttempt(ChallengeAttemptRequest challengeAttemptRequest);
}
