package microservices.book.multiplication.infrastructure.adapters.output.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.ports.output.ResponseError;
import microservices.book.multiplication.domain.exception.ChallengeAttemptNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
    private static final String REQUESTED_CHALLENGE_ATTEMPT_NOT_FOUND = "Requested challenge attempt not found";

    @ExceptionHandler(ChallengeAttemptNotFoundException.class)
    public ResponseEntity<Object> handleChallengeAttemptNotFoundException(ChallengeAttemptNotFoundException ex) {
        log.error(REQUESTED_CHALLENGE_ATTEMPT_NOT_FOUND);

       return buildResponseEntity(new ResponseError(HttpStatus.NOT_FOUND, REQUESTED_CHALLENGE_ATTEMPT_NOT_FOUND));
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseError responseError) {
        return new ResponseEntity<>(responseError, responseError.getStatus());
    }
}
