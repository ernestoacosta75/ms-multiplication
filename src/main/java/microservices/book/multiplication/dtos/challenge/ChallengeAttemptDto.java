package microservices.book.multiplication.dtos.challenge;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

/**
 * Attempt coming from the user.
 * The @Value annotation is used to create an immutable class,
 * with an all-args constructor and toString, equals, and hashCode methods.
 * It'all also set the fields to be private final
 */
@Value
public class ChallengeAttemptDto {
    @Min(1) @Max(99)
    int factorA, factorB;

    @NotBlank
    String userAlias;

    @Positive(message = "How could you possibly get a negative result here? Try again.")
    int guess;
}