package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.application.ports.input.command.CreateChallengeAttemptCommand1;
import microservices.book.multiplication.domain.model.ChallengeAttempt;

public interface IChallengeAttemptService {
    ChallengeAttempt createChallengeAttempt(CreateChallengeAttemptCommand1 createChallengeAttemptCommand);
    void deleteChallengeAttempt(Long challengeAttemptId);
}
