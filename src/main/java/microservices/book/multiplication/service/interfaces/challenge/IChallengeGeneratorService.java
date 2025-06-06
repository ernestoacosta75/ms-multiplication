package microservices.book.multiplication.service.interfaces.challenge;

import microservices.book.multiplication.domain.Challenge;

public interface IChallengeGeneratorService {

    /**
     * @return a randomly-generated challenge with factors between 11 and 99
     */
    Challenge randomChallenge();
}
