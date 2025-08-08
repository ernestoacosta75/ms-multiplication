package microservices.book.multiplication.application.dto;

import lombok.*;
import microservices.book.multiplication.domain.model.user.User;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChallengeAttemptResponseDto {
    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
