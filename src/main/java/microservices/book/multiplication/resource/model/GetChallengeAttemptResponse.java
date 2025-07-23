package microservices.book.multiplication.resource.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import microservices.book.multiplication.application.domain.model.User;

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
