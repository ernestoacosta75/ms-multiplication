package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.domain.model.challenge.Challenge;

public interface IChallengeGeneratorService {

    /**
     * @return a randomly-generated challenge with factors between 11 and 99
     */
    Challenge randomChallenge();
}
