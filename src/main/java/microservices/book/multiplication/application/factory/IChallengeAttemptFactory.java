package microservices.book.multiplication.application.factory;

import microservices.book.multiplication.application.ports.input.command.IChallengeAttemptCommand;
import microservices.book.multiplication.application.ports.input.query.IChallengeAttemptQuery;

public interface IChallengeAttemptFactory {
    IChallengeAttemptCommand getChallengeAttemptCommand();
    IChallengeAttemptQuery getChallengeAttemptQuery();
}
