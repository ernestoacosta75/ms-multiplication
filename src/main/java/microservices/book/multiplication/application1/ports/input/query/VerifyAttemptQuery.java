package microservices.book.multiplication.application.ports.input.query;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;

import java.util.Objects;

@Builder
public record VerifyAttemptQuery (
    @NotBlank(message = "Query name is required")
    String queryName,

    @NotBlank(message = "Challenge attempt request is required")
    ChallengeAttemptRequest challengeAttemptRequest
){
    public VerifyAttemptQuery {
        Objects.requireNonNull(queryName);
        Objects.requireNonNull(challengeAttemptRequest);
    }
}
