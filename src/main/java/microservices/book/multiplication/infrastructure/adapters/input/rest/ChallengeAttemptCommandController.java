package microservices.book.multiplication.infrastructure.adapters.input.rest;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.ChallengeDto;
import microservices.book.multiplication.application.ports.mapper.ChallengeDtoMapper;
import microservices.book.multiplication.application.ports.service.IChallengeAttemptCommandService;
import microservices.book.multiplication.domain.model.ChallengeAttemptAggregate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * This class provides a REST API to post the attempts from users.
 * The @Valid annotation allows to integrate the constraints added in the DTO
 * with Spring in the controller's method argument.
 */
@Slf4j
@RequiredArgsConstructor
@Api(value = "Challenge Attempt Commands", description = "Challenge Attempt Commands API")
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptCommandController {
    private final IChallengeAttemptCommandService challengeAttemptCommandService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<ChallengeAttemptAggregate> postResult(@RequestBody @Valid ChallengeDto challengeDto) {
        return this.challengeAttemptCommandService.createChallengeAttempt(ChallengeDtoMapper.MAPPER.map(challengeDto), challengeDto.getUserAlias());
    }
}
