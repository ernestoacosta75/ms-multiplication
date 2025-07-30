package microservices.book.multiplication.persistence.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The @Data annotation from Lombok groups "equals" and "hashCode" methods, "toString",
 * getters, and also setters.
 * JPA and Hibernate also require the entity to have a default, empty constructor (achieved with Lombokìs@NoArgsConstructor annotation)
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String alias;

    public UserEntity(final String userAlias) {
        this(null, userAlias);
    }
}
