package microservices.book.multiplication.application.service;

import microservices.book.multiplication.application.domain.model.Challenge;

public interface IChallengeGeneratorService {

    /**
     * @return a randomly-generated challenge with factors between 11 and 99
     */
    Challenge randomChallenge();
}
