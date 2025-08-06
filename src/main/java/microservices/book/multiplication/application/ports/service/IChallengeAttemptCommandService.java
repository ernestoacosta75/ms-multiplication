package microservices.book.multiplication.application.ports.service;

import microservices.book.multiplication.domain.model.Challenge;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;

import java.util.concurrent.CompletableFuture;

public interface IChallengeAttemptCommandService {
    CompletableFuture<ChallengeAttemptAggregate> createChallengeAttempt(Challenge challenge, String userAlias);
}
