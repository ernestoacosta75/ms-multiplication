package microservices.book.multiplication.domain.service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication.domain.model.User;
import microservices.book.multiplication.application.factory.IChallengeAttemptFactory;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.application.ports.input.mapper.ChallengeRequestMapper;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptRequest;
import microservices.book.multiplication.infrastructure.adapters.input.rest.model.ChallengeAttemptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChallengeService implements IChallengeService {

    @Autowired
    private IChallengeAttemptFactory challengeAttemptFactory;

    @Override
    public ChallengeAttemptResponse verifyAttempt(ChallengeAttemptRequest challengeAttemptRequest) {
        // Checking if attempt is correct
        boolean isCorrect = challengeAttemptRequest.getGuess() ==
                challengeAttemptRequest.getFactorA() * challengeAttemptRequest.getFactorB();

        // We don't use identifiers for now
        User user = new User(null, challengeAttemptRequest.getUserAlias());

        // Building the domain object. Null id for now
        var challengeAttempt = ChallengeRequestMapper.MAPPER.map(challengeAttemptRequest);
        var challengeAttemptCommand = challengeAttemptFactory.getChallengeAttemptCommand();

        ChallengeAttemptResponse challengeAttemptResponse = ChallengeAttemptResponse.builder()
                .id(null)
                .user(user)
                .factorA(challengeAttemptRequest.getFactorA())
                .factorB(challengeAttemptRequest.getFactorB())
                .correct(isCorrect)
                .build();

        return challengeAttemptResponse;
    }
}
