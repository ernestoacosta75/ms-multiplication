package microservices.book.multiplication.resource.model;

import lombok.*;
import microservices.book.multiplication.application.domain.model.User;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChallengeAttemptResponse {
    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
