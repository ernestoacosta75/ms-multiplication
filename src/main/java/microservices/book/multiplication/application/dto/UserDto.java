package microservices.book.multiplication.application.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String alias;
}
