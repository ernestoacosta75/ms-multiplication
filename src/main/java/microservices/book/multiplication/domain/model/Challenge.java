package microservices.book.multiplication.domain.model;

import lombok.*;

/**
 * This class represents a Challenge to solve a Multiplication. (a * b).
 */
@Data
@AllArgsConstructor
public class Challenge {
    private int factorA;
    private int factorB;
}
