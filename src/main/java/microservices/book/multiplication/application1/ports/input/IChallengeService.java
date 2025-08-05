package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.application.ports.input.command.CreateChallengeAttemptCommand1;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;

public interface IChallengeService {

    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * @param challengeAttemptRequest
     * @return the resulting {@link ChallengeAttemptResponse} object
     */
    ChallengeAttemptResponse verifyAttempt(CreateChallengeAttemptCommand1 createChallengeAttemptCommand);
}
