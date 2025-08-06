package microservices.book.multiplication.application1.ports.input.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor
public class FindChallengeAttemptQuery {
    private Long challengeAttemptId;
}
