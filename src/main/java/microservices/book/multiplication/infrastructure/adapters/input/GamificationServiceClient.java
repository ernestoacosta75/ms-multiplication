package microservices.book.multiplication.infrastructure.adapters.input;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.application.dto.ChallengeSolvedDto;
import microservices.book.multiplication.application.ports.input.IGamificationServiceClient;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class represents a TEST API Client and implements {@link IGamificationServiceClient} that uses
 * a {@link RestTemplate} to send the attempt to the Gamification service.
 *
 * This new Spring@Service can be injected in the existing ones.
 * It uses the builder to initialize the RestTemplate with defaults (just calling bluild()).
 * It also accepts in the constructor the host URL of the Gamification microservice, which we want
 * to extract as a configuration parameter.
 */
@Slf4j
@Service
public class GamificationServiceClient implements IGamificationServiceClient {
    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;

    public GamificationServiceClient(final RestTemplateBuilder builder,
                                     @Value("${service.gamification.host}") final String gamificationHostUrl) {
        this.restTemplate = builder.build();
        this.gamificationHostUrl = gamificationHostUrl;
    }

    @Override
    public boolean sendAttempt(ChallengeAttemptEntity challengeAttemptEntity) {
        try {
            ChallengeSolvedDto challengeSolvedDto = new ChallengeSolvedDto(
                    challengeAttemptEntity.getId(),
                    challengeAttemptEntity.isCorrect(),
                    challengeAttemptEntity.getFactorA(),
                    challengeAttemptEntity.getFactorB(),
                    challengeAttemptEntity.getUser().getId(),
                    challengeAttemptEntity.getUser().getAlias());

            // Using the postForEntity() method to send a POST request to the Gamification microservice.
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    gamificationHostUrl + "/attempts",
                    challengeSolvedDto,
                    String.class);

            log.info("Gamification service response: {}", responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt: {}", e.getMessage());
            return false;
        }
    }
}
