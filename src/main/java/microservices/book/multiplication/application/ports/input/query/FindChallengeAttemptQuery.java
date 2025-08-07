package microservices.book.multiplication.application.ports.input.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindChallengeAttemptQuery extends BaseQuery<Long>{

    public FindChallengeAttemptQuery(Long queryId) {
        super(queryId);
    }
}
