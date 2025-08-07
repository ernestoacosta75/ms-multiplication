package microservices.book.multiplication.infrastructure.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.ports.service.IChallengeGeneratorService;
import microservices.book.multiplication.domain.model.Challenge;
import org.springframework.web.bind.annotation.*;

/**
 * This class implements a REST API to get random challenges.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    private final IChallengeGeneratorService challengeGeneratorService;

    @GetMapping("/random")
    public Challenge getRandomChallenge() {
        Challenge challenge = challengeGeneratorService.randomChallenge();
        log.info("Generating a random challenge: {}", challenge);

        return challenge;
    }
}
