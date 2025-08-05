package microservices.book.multiplication.application.ports.input.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;

import java.util.Objects;

@Builder
public record CreateChallengeAttemptCommand1(
        @NotBlank(message = "Command name is required")
        String commandName,

        @NotBlank(message = "The challenge attempt request is required")
        ChallengeAttemptRequest challengeAttemptRequest
) {
    public CreateChallengeAttemptCommand1 {
        Objects.requireNonNull(commandName);
        Objects.requireNonNull(challengeAttemptRequest);
    }
}
