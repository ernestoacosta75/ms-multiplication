package microservices.book.multiplication.application.ports.input.command;

import microservices.book.multiplication.domain.model.ChallengeAttempt;

public interface IChallengeAttemptCommand {
    ChallengeAttempt createChallengeAttempt(ChallengeAttempt challengeAttempt);
}
