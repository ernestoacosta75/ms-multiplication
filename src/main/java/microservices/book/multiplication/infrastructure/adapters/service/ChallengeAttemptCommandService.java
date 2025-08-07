package microservices.book.multiplication.infrastructure.adapters.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.ports.input.command.CreateChallengeCommand;
import microservices.book.multiplication.application.ports.mapper.UserDtoMapper;
import microservices.book.multiplication.application.ports.service.IChallengeAttemptCommandService;
import microservices.book.multiplication.domain.model.Challenge;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ChallengeAttemptCommandService implements IChallengeAttemptCommandService {
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<ChallengeAttemptAggregate> createChallengeAttempt(Challenge challenge, String userAlias) {

        return this.commandGateway.send(new CreateChallengeCommand(
                UUID.randomUUID().node(),
                UserDtoMapper.MAPPER.map(new UserDto(null, userAlias)),
                challenge.getFactorA(),
                challenge.getFactorB()
        ));
    }
}
