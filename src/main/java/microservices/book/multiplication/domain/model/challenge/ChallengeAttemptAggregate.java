package microservices.book.multiplication.domain.model.challenge;

import microservices.book.multiplication.domain.model.user.User;

import java.util.Objects;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 * It's linked to the user via userId.
 * There are both challenge factors too. This is done to avoid to have
 * a reference to a {@link Challenge} via challengeId, because we can
 * simply generate new challenges "on the fly" and copy them here to keep
 * the data structures simple.
 */
public class ChallengeAttemptAggregate {
    private Long id;
    private User user;
    private Challenge challenge;
    private int resultAttempt;
    private boolean correct;

    private ChallengeAttemptAggregate(Long id, User user, Challenge challenge,
                                     int resultAttempt, boolean correct) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }

    private ChallengeAttemptAggregate(Long id, User user, Challenge challenge, boolean correct) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.correct = correct;
    }

    public static ChallengeAttemptAggregate create(Long id, User user, Challenge challenge,
                                                   int resultAttempt, boolean correct) {

        validateInput(user, challenge, resultAttempt);
        return new ChallengeAttemptAggregate(id, user, challenge, resultAttempt, correct);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setResultAttempt(int resultAttempt) {
        this.resultAttempt = resultAttempt;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeAttemptAggregate that = (ChallengeAttemptAggregate) o;

        return resultAttempt == that.resultAttempt &&
                correct == that.correct &&
                Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(challenge, that.challenge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, challenge, resultAttempt, correct);
    }

    private static void validateInput( User user, Challenge challenge, int resultAttempt){
        if (user == null) {
            throw new IllegalArgumentException("A user is required.");
        }

        if (challenge == null) {
            throw new IllegalArgumentException("A challenge is required.");
        }

        if (resultAttempt < 0) {
            throw new IllegalArgumentException("The result attempt can't be a negative value");
        }
    }
}
