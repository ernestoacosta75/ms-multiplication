package microservices.book.multiplication.application.factory.impl;

import microservices.book.multiplication.application.ports.input.command.IChallengeAttemptCommand;
import microservices.book.multiplication.application.factory.IChallengeAttemptFactory;
import microservices.book.multiplication.application.ports.input.query.IChallengeAttemptQuery;

public class ChallengeAttemptFactory implements IChallengeAttemptFactory {

    private final IChallengeAttemptCommand challengeAttemptCommand;
    private final IChallengeAttemptQuery challengeAttemptQuery;

    public ChallengeAttemptFactory(IChallengeAttemptCommand challengeAttemptCommand, IChallengeAttemptQuery challengeAttemptQuery) {
        this.challengeAttemptCommand = challengeAttemptCommand;
        this.challengeAttemptQuery = challengeAttemptQuery;
    }

    @Override
    public IChallengeAttemptCommand getChallengeAttemptCommand() {
        return challengeAttemptCommand;
    }

    @Override
    public IChallengeAttemptQuery getChallengeAttemptQuery() {
        return challengeAttemptQuery;
    }
}
