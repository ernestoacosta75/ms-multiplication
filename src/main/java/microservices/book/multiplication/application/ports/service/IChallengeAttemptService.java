package microservices.book.multiplication.application.ports.service;

import microservices.book.multiplication.application.dto.ChallengeAttemptDto;
import microservices.book.multiplication.domain.model.ChallengeAttempt;

import java.util.concurrent.CompletableFuture;

public interface IChallengeAttemptService {
    CompletableFuture<ChallengeAttempt> createChallengeAttempt(ChallengeAttemptDto challengeAttemptDto);
}
