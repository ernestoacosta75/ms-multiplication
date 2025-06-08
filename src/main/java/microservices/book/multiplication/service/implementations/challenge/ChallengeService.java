package microservices.book.multiplication.service.implementations.challenge;

import microservices.book.multiplication.domain.ChallengeAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.dtos.challenge.ChallengeAttemptDto;
import microservices.book.multiplication.service.interfaces.challenge.IChallengeService;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService implements IChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto challengeAttemptDto) {
        // Checking if attempt is correct
        boolean isCorrect = challengeAttemptDto.getGuess() ==
                challengeAttemptDto.getFactorA() * challengeAttemptDto.getFactorB();

        // We don't use identifiers for now
        User user = new User(null, challengeAttemptDto.getUserAlias());

        // Building the domain object. Null id for now
        ChallengeAttempt checkedAttempt = ChallengeAttempt.builder()
                .id(null)
                .userId(user.getId())
                .factorA(challengeAttemptDto.getFactorA())
                .factorB(challengeAttemptDto.getFactorB())
                .correct(isCorrect)
                .build();

        return checkedAttempt;
    }
}
