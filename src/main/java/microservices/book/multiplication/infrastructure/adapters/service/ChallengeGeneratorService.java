package microservices.book.multiplication.infrastructure.adapters.service;

import microservices.book.multiplication.application.ports.service.IChallengeGeneratorService;
import microservices.book.multiplication.domain.model.Challenge;

import java.util.Random;

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
        return new Challenge(next(), next(), 0);
    }

    private int next() {
        return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }
}
