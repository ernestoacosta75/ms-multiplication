package microservices.book.multiplication.application.dto;

import lombok.Value;

@Value
public class UserDto {
    private Long id;
    private String alias;
}
