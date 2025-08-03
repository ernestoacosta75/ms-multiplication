package microservices.book.multiplication.domain.model;

import lombok.*;

/**
 * Stores information to identify the user.
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String alias;
}
