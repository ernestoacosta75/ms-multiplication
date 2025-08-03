package microservices.book.multiplication.infrastructure.adapters.output.persistence.repository;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.application.ports.output.persistence.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final IUserJpaRepository userJpaRepository;
}
