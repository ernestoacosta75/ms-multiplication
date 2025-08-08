package microservices.book.multiplication.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import microservices.book.multiplication.domain.model.user.User;

@Setter
@Getter
@ToString
public class GetChallengeAttemptResponse {
    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
