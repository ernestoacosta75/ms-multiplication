package microservices.book.multiplication.application.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides a REST API to post the attempts from users.
 * The @Valid annotation allows to integrate the constraints added in the DTO
 * with Spring in the controller's method argument.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    private final IChallengeService challengeService;

    @GetMapping
    ResponseEntity<List<ChallengeAttemptResponseDto>> getStatsForUser(@RequestParam String alias) {
        return ResponseEntity.ok(challengeService.getStatsForUser(alias));
    }

    @PostMapping
    ResponseEntity<ChallengeAttemptResponseDto> postResult(@RequestBody @Valid ChallengeAttemptRequestDto challengeAttemptRequestDto) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptRequestDto));
    }
}
