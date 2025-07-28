package microservices.book.multiplication.application.factory;

import microservices.book.multiplication.application.command.IChallengeAttemptCommand;
import microservices.book.multiplication.application.query.IChallengeAttemptQuery;

public interface IChallengeAttemptFactory {
    IChallengeAttemptCommand getChallengeAttemptCommand();
    IChallengeAttemptQuery getChallengeAttemptQuery();
}
