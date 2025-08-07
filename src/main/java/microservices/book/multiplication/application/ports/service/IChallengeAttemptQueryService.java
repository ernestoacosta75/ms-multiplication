package microservices.book.multiplication.application.ports.service;

import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;

import java.util.concurrent.CompletableFuture;

public interface IChallengeAttemptQueryService {
    CompletableFuture<ChallengeAttemptAggregate> findById(Long challengeAttemptId);
}
