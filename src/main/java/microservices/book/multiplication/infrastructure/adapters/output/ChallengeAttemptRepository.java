package microservices.book.multiplication.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.IChallengeAttemptRepository;
import microservices.book.multiplication.infrastructure.adapters.output.crud.IChallengeAttemptCrudRepository;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChallengeAttemptRepository implements IChallengeAttemptRepository {

    private final IChallengeAttemptCrudRepository challengeAttemptCrudRepository;

    @Override
    public List<ChallengeAttemptEntity> findTop10ByUserAliasOrderByIdDesc(String userAlias) {
        return this.challengeAttemptCrudRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public ChallengeAttemptEntity save(ChallengeAttemptEntity entity) {
        return this.challengeAttemptCrudRepository.save(entity);
    }
}
