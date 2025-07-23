package microservices.book.multiplication.application.service.impl;

import microservices.book.multiplication.application.domain.model.Challenge;
import microservices.book.multiplication.application.service.IChallengeGeneratorService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChallengeGeneratorService implements IChallengeGeneratorService {
    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;
    private final Random random;

    public ChallengeGeneratorService() {
        this.random = new Random();
    }

    /**
     * This second constructor make the class testable
     * accepting a random object as parameter.
     * @param random
     */
    public ChallengeGeneratorService(Random random) {
        this.random = random;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(next(), next());
    }

    private int next() {
        return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }
}
