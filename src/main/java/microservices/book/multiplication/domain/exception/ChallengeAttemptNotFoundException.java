package microservices.book.multiplication.domain.exception;

public class ChallengeAttemptNotFoundException extends Throwable {
    public ChallengeAttemptNotFoundException(Long id) {
        super("Cannot found challenge attempt with id [" + id + "]");
    }
}
