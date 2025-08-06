package microservices.book.multiplication.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class ChallengeDto {
    @Min(1) @Max(99)
    private int factorA;

    @Min(1) @Max(99)
    private int factorB;

    @Positive(message = "Hoe could you possibly get a negative result here? Try again.")
    private int guess;

    private String userAlias;
}
