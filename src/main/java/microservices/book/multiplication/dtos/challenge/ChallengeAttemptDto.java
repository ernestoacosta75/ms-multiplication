package microservices.book.multiplication.dtos.challenge;

import lombok.Value;

/**
 * Attempt coming from the user.
 * The @Value annotation is used to create an immutable class,
 * with an all-args constructor and toString, equals, and hashCode methods.
 * It'all also set the fields to be private final
 */
@Value
public class ChallengeAttemptDto {
    int factorA;
    int factorB;
    String userAlias;
    int guess;
}