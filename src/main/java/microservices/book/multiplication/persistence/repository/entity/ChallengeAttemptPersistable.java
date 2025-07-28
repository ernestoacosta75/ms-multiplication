package microservices.book.multiplication.persistence.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
@Entity
@Table(name = "challenge_attempt")
public class ChallengeAttemptPersistable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
