package microservices.book.multiplication.application.dto;

import lombok.Value;
import microservices.book.multiplication.domain.model.User;

@Value
public class ChallengeAttemptDto {
    private Long id;
    private User user;
    private ChallengeDto challenge;
    private int resultAttempt;
    private boolean correct;
}
