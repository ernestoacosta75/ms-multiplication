package microservices.book.multiplication.infrastructure.adapters.output.crud;

import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IChallengeAttemptCrudRepository extends CrudRepository<ChallengeAttemptEntity, Long> {
    /**
     *
     * @param userAlias
     * @return the last 10 attempts for a given user, identified by their alias.
     */
    List<ChallengeAttemptEntity> findTop10ByUserAliasOrderByIdDesc(String userAlias);

    @Query("select ca from ChallengeAttemptEntity ca join fetch ca.user")
    List<ChallengeAttemptEntity> getAllChallenges();
}
