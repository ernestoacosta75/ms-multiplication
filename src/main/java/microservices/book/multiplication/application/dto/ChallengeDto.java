package microservices.book.multiplication.application.dto;

import lombok.Value;

@Value
public class ChallengeDto {
    private int factorA;
    private int factorB;
}
