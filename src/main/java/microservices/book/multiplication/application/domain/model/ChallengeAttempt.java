package microservices.book.multiplication.application.domain.model;

import lombok.*;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 * It's linked to the user via userId.
 * There are both challenge factors too. This is done to avoid to have
 * a reference to a {@link Challenge} via challengeId, because we can
 * simply generate new challenges "on the fly" and copy them here to keep
 * the data structures simple.
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ChallengeAttempt {
    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
