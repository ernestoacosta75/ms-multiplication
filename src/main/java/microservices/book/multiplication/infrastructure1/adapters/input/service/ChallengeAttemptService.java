package microservices.book.multiplication.infrastructure1.adapters.input.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.application.dto.ChallengeAttemptDto;
import microservices.book.multiplication.application.ports.input.command.CreateChallengeAttemptCommand;
import microservices.book.multiplication.application.ports.mapper.ChallengeAttemptDtoMapper;
import microservices.book.multiplication.application.ports.mapper.UserDtoMapper;
import microservices.book.multiplication.application.ports.service.IChallengeAttemptService;
import microservices.book.multiplication.domain.model.ChallengeAttempt;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ChallengeAttemptService implements IChallengeAttemptService {
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<ChallengeAttempt> createChallengeAttempt(ChallengeAttemptDto challengeAttemptDto) {
        var x = this.commandGateway.send(new CreateChallengeAttemptCommand(
                UUID.randomUUID().node(),
                UserDtoMapper.MAPPER.map(challengeAttemptDto.getUser()),
        ));
    }
}
