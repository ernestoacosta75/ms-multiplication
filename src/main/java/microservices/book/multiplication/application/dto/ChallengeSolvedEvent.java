package microservices.book.multiplication.application.dto;

import lombok.Value;

/**
 * This DTO is a copy of the one created in the Gamification microservice.
 * It represents the data that we send to the Gamification microservice
 * when a multiplication challenge is solved.
 */
@Value
public class ChallengeSolvedEvent {
    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;
}
