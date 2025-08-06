package microservices.book.multiplication.domain1.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.application.ports.input.command.CreateChallengeAttemptCommand1;
import microservices.book.multiplication.domain.model.User;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChallengeService implements IChallengeService {

    @Override
    public ChallengeAttemptResponse verifyAttempt(CreateChallengeAttemptCommand1 createChallengeAttemptCommand) {
        // Checking if attempt is correct
        boolean isCorrect = createChallengeAttemptCommand.challengeAttemptRequest().getGuess() ==
                createChallengeAttemptCommand.challengeAttemptRequest().getFactorA() * createChallengeAttemptCommand.challengeAttemptRequest().getFactorB();

        // We don't use identifiers for now
        User user = new User(null, createChallengeAttemptCommand.challengeAttemptRequest().getUserAlias());

        // Building the domain object. Null id for now
        //var challengeAttempt = ChallengeRequestMapper.MAPPER.map(createChallengeAttemptCommand.challengeAttemptRequest());

        ChallengeAttemptResponse challengeAttemptResponse = ChallengeAttemptResponse.builder()
                .id(null)
                .user(user)
                .factorA(createChallengeAttemptCommand.challengeAttemptRequest().getFactorA())
                .factorB(createChallengeAttemptCommand.challengeAttemptRequest().getFactorB())
                .correct(isCorrect)
                .build();

        return challengeAttemptResponse;
    }
}
