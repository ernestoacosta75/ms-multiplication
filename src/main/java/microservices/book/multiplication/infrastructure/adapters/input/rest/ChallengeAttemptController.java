package microservices.book.multiplication.infrastructure.adapters.input.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides a REST API to post the attempts from users.
 * The @Valid annotation allows to integrate the constraints added in the DTO
 * with Spring in the controller's method argument.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    private final IChallengeService challengeService;

    @PostMapping
    ResponseEntity<ChallengeAttemptResponse> postResult(@RequestBody @Valid ChallengeAttemptRequest challengeAttemptRequest) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptRequest));
    }
}
