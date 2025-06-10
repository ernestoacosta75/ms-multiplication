package microservices.book.multiplication.controllers.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.Challenge;
import microservices.book.multiplication.service.interfaces.challenge.IChallengeGeneratorService;
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
