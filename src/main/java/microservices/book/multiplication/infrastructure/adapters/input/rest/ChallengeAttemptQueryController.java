package microservices.book.multiplication.infrastructure.adapters.input.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Api(value = "Challenge Attempt Queries", description = "Challenge Attempt Query Events API")
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptQueryController {

    @GetMapping("/{challengeAttemptId}")
    public CompletableFuture<ChallengeAttemptAggregate> findById(@PathVariable("challengeAttemptId") Long challengeAttemptId) {
        return null;
    }
}
