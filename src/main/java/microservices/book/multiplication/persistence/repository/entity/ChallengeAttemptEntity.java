package microservices.book.multiplication.persistence.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Setting the fetch type to LAZY in the @ManyToOne annotation,
 * the queries to retrieve those fields will be executed  only when
 * we try to access them.
 * Otherwise, with fecth type as EAGER, the user data gets collected with the attempt.
 *
 * This works because Hibernate configures proxy classes for the entity classes.
 * These pro
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "challenge_attempts")
public class ChallengeAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
