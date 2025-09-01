* The project contains a **Maven wrapper**. This is a standalone-version
  of Maven so we don't need to install it to build the application.
  These are the **.mvn** folder and the **mnvw** executables for Windows.

## @SpringBootApplication annotation
* It's a shortcut annotation that groups several others, among them **@EnableAutoConfiguration**.
* With it, Spring activates this smart mechanism and finds and processes classes annotated with the **@Configuration** annotation,
  from our code, but also from our dependencies.

## Three-tier, three-layer architecture
These three tiers are as follows:
* **Client tier**: This tier is responsible for the user interface. Typically, this
  is what we call the **front end**.
* **Application tier**: This contains all the business logic together with the
  interfaces to interact with it and the data interfaces for persistence.
  This maps to what we call the **back end**.
  * Here, we can create the following packages:
    * **controller**: It will be the **Presentation layer**.
    * **service**: It will be the **Business Logic layer**.
    * **repository**: It will be the **Data Access layer**.
    * **domain**: It will be the **Domain Models**.
    * **config**: It will be the **Configuration Classes**.
* **Data store tier**: It’s the database, file system, etc., that persists the
  application’s data.

## Useful Spring stereotype annotations
* The **@Controller** annotation is for the presentation layer. In our case,
  we’ll implement a REST interface using controllers.
* The **@Service** annotation is for classes implementing business logic.
* The **@Repository** annotation is for the data layer, namely, the classes
  that interact with the database.

When we annotate classes with these variants, they become **Spring-managed
components**.

When initializing the web context, Spring scans your packages, finds these
classes, and **loads them as beans in the context**. Then, we can use **dependency injection
to wire (or inject) these beans** and, for example, use services from our presentation layer
(controllers).

## Useful testing annotations

* **Spy**
  It is used to create a "spy" object, which is a partial mock of a real object.
  The main use cases for this annotation are:
  1. **Partial Mocking**: When we want to mock only certain methods of an object, while allowing the rest of th
    object's methods to behave as the original implementation. This is useful when we want to test a specific
    behavior of an object without having to mock the entire object.
  2. **Verifying behavior**: With a spy, we can verify the behavior of the real object, such as the number of times
     method was called, the arguments passed to the method, etc.
  3. **Stubbing behavior**: We can also stub the behavior of specific methods on the real object, while allowing
     the rest of the object to behave as the original implementation

* **@ExtendWith(SpringExtension.class)**: Makes sure that JUnit 5 test loads the extensions for Spring so we can use a test context.

* **@AutoConfigureJsonTesters**: Tells Spring to configure beans of type JacksonTester from some fields we declare in the test. 
  A JacksonTester may be used to serialize and deserialize objects using the same configuration (ex.: ObjectMapper) as the app
  would do in runtime.

* **@WebMvcTest**: With the controller class as parameter, makes Spring treates this as a presentation layer. Thus, it will load only
  the relevant configuration around the controller: validation, serializers, security, error handlers, etc.

* **@MockBean**: Allow to mock other layers and beans we're not testing. The expected return values within the test methods, is set
  using *BDDMockito's given()*.

* **MockMvc**: It's what we use in Spring to simulate requests to the presentation layer when we make a test that doesn't load a real server.
  It's provided by the test context so we can inject it in our tests.

# Implementing Microservices using Hexagonal Architecture

The hexagonal architecture allows you to have a **separation of concerns**. 
In short, it says the application and domain layer contains core business logic. Therefore, **it should not depend on infrastructure concerns such as database and messaging**. You are free to change your technology choices while still keeping business logic intact.

Hexagonal architecture is also known as **ports and adapters**. 
This architecture defines ports (**interfaces**) in the **application/domain** layer and provides the implementation in different layers. The **adapters** will be the implementation of the **ports**. 
Therefore, the application layer is completely unaware of implementation. This allows you to change, for instance, the database without changing business logic.

In Hexagonal architecture, we can organize code in **layers per package** approach as:

**Resource** (uses **Application**)
* Controller
* Adapter

**Application**
* Commands
* Domain
* Factory
* Query
* Service

**Infrastructure** (uses **Application**)
* Gateway
* Adapter

**Persistence** (uses **Application**)
* Repository
* Adapter

**Domain**
* Service
* Factory
* Model
* Repository

### Adapters:
Adapters are responsible for converting data between the external world and the internal application core (domain).
There are two main types of adapters:

**a) Primary (Driving) Adapters**: Convert external requests into domain operations
* REST Controllers
* GraphQL Resolvers
* Message Listeners 

** b) Secondary (Driven) Adapters**: Convert domain operations to external system interactions

* Database Repositories
* External API Clients
* Message Producers

### Gateways:
* Gateways are interfaces that define contracts for external system interactions
* They abstract the implementation details of external system communication
* Typically used for database access, external service calls, etc.



## Package Descriptions

### Domain Layer
- `model`: Contains core domain entities (ex.: **User.java**)
- `service`: Domain-specific business logic (ex.: **UserDomainService.java**)
- `repository`: Domain repository interfaces (ex.: **IUserRepository.java**)

### Application Layer
- `service`: Application-level services (ex.: **UserApplicationService.java**)
- `command`: Command objects for creating/modifying entities (ex.: **CreateUserCommand.java**)
- `query`: Query services for retrieving data (ex.: **UserQueryService.java**)

### Infrastructure Layer
- `adapter`: External service adapters (ex.: **ExternalUserServiceAdapter.java**)
- `gateway`: Service gateways for external integrations (ex.: **ExternalUserServiceGateway.java**)

### Persistence Layer
- `adapter`: Persistence-specific adapters (ex.: **UserPersistenceAdapter.java**)
- `repository`: JPA repository implementations (ex.: **UserJpaRepository.java**)

### Resource Layer
- `adapter`: REST-specific adapters (ex.: **UserRestAdapter.java**)
- `controller`: REST controllers for handling HTTP requests (ex.: **UserController.java**)

## Example implementation

1. Domain Model:

```
package com.example.usermanagement.domain.model;

public class User {
    private Long id;
    private String username;
    private String email;

    // Constructors, getters, setters
}
```
2. Domain Service:
```
package com.example.usermanagement.domain.service;

import com.example.usermanagement.domain.model.User;
import com.example.usermanagement.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {
    private final IUserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Domain-specific validation logic
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Business rules and validations
        validateUserCreation(user);

        return userRepository.save(user);
    }

    private void validateUserCreation(User user) {
        // Complex domain-specific validation rules
        if (user.getUsername().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }
    }

    public User updateUserStatus(Long userId, boolean active) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Domain-specific status update logic
        user.setActive(active);
        return userRepository.save(user);
    }
}
```

3. Domain Repository Interface:
```
package com.example.usermanagement.domain.repository;

import com.example.usermanagement.domain.model.User;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
}
```

4. Application Command:
```
package com.example.usermanagement.application.command;

public class CreateUserCommand {
    private String username;
    private String email;

    // Constructors, getters, setters
}
```

5. Application Service:
```
package com.example.usermanagement.application.service;

import com.example.usermanagement.domain.model.User;
import com.example.usermanagement.domain.repository.IUserRepository;
import com.example.usermanagement.application.command.CreateUserCommand;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {
    private final IUserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setEmail(command.getEmail());
        return userRepository.save(user);
    }
}
```
6. Infrastructure Adapter:
```
package com.example.usermanagement.infrastructure.adapter;

import com.example.usermanagement.domain.model.User;
import com.example.usermanagement.infrastructure.gateway.ExternalUserServiceGateway;
import org.springframework.stereotype.Component;

@Component
public class ExternalUserServiceAdapter {
    private final ExternalUserServiceGateway externalUserServiceGateway;

    public ExternalUserServiceAdapter(ExternalUserServiceGateway externalUserServiceGateway) {
        this.externalUserServiceGateway = externalUserServiceGateway;
    }

    public boolean validateUser(User user) {
        // Adapt domain model to external service validation
        return externalUserServiceGateway.validateUserExternal(user.getUsername());
    }

    public void syncUserToExternalSystem(User user) {
        // Additional transformation or validation before syncing
        if (user.isActive()) {
            externalUserServiceGateway.syncUserToExternalSystem(user);
        }
    }
}
```
7. Infrastructure Gateway:
```
package com.example.usermanagement.infrastructure.gateway;

import com.example.usermanagement.domain.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;

@Component
public class ExternalUserServiceGateway {
    private final RestTemplate restTemplate;

    @Value("${external.user.service.url}")
    private String externalUserServiceUrl;

    @Value("${external.user.service.api.key}")
    private String apiKey;

    public ExternalUserServiceGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Validation Request DTO
    public static class ValidationRequest {
        private String username;

        public ValidationRequest(String username) {
            this.username = username;
        }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }

    // Validation Response DTO
    public static class ValidationResponse {
        private boolean valid;
        private String message;

        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    // User Sync Request DTO
    public static class UserSyncRequest {
        private String username;
        private String email;
        private String firstName;
        private String lastName;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }

    // External User Details DTO
    public static class ExternalUserDetails {
        private String username;
        private String email;
        private String externalId;
        private boolean verified;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getExternalId() { return externalId; }
        public void setExternalId(String externalId) { this.externalId = externalId; }
        public boolean isVerified() { return verified; }
        public void setVerified(boolean verified) { this.verified = verified; }
    }

    // Custom Exception for External Service Sync
    public static class ExternalServiceSyncException extends RuntimeException {
        public ExternalServiceSyncException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Method to validate user with external service
    public boolean validateUserExternal(String username) {
        try {
            String validationUrl = externalUserServiceUrl + "/validate";
            
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-API-KEY", apiKey);

            // Prepare request body
            ValidationRequest request = new ValidationRequest(username);
            HttpEntity<ValidationRequest> entity = new HttpEntity<>(request, headers);

            // Make the call
            ResponseEntity<ValidationResponse> response = restTemplate.postForEntity(
                validationUrl, 
                entity, 
                ValidationResponse.class
            );

            return response.getBody() != null && response.getBody().isValid();
        } catch (Exception e) {
            // Log the error            
```

8. Persistence Adapter:
```
package com.example.usermanagement.persistence.adapter;

import com.example.usermanagement.domain.model.User;
import com.example.usermanagement.domain.repository.IUserRepository;
import com.example.usermanagement.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements IUserRepository {
    private final UserJpaRepository jpaRepository;

    public UserPersistenceAdapter(UserJpa
```

9. Resource Adapter:
```
package com.example.usermanagement.resource.adapter;

import com.example.usermanagement.application.service.UserApplicationService;
import com.example.usermanagement.domain.model.User;
import com.example.usermanagement.resource.dto.UserRequestDTO;
import com.example.usermanagement.resource.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRestAdapter {
    private final UserApplicationService userApplicationService;

    public UserRestAdapter(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // Convert DTO to Domain Model and process creation
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Map DTO to Domain Model
        User user = mapToUserDomain(userRequestDTO);

        // Delegate to application service
        User createdUser = userApplicationService.createUser(user);

        // Map created user back to response DTO
        return mapToUserResponseDTO(createdUser);
    }

    // Convert DTO to Domain Model
    private User mapToUserDomain(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    // Convert Domain Model to Response DTO
    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    // Additional methods for other operations
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        // Similar pattern of conversion and delegation
        User userToUpdate = mapToUserDomain(userRequestDTO);
        userToUpdate.setId(userId);

        User updatedUser = userApplicationService.updateUser(userToUpdate);
        return mapToUserResponseDTO(updatedUser);
    }

    public void deleteUser(Long userId) {
        userApplicationService.deleteUser(userId);
    }

    // Validation method that can be used before processing
    public boolean validateUserRequest(UserRequestDTO userRequestDTO) {
        // Additional validation logic specific to REST layer
        return userRequestDTO != null 
               && userRequestDTO.getUsername() != null 
               && !userRequestDTO.getUsername().isEmpty()
               && userRequestDTO.getEmail() != null 
               && userRequestDTO.getEmail().contains("@");
    }
}
```

# Adding CORS configuration to the Spring Boot App

By default, your browser blocks requests that try to access resources in a different
domain than the one in which your front end is located. This is to avoid that a malicious
page in your browser has access to data in a different page, and it’s called the same-origin
policy. In our case, we’re running both the front end and the back end in localhost, but
they run on different ports, so they are considered different origins.
To fix this, we’re going to enable **cross-origin
resource sharing** (CORS), **a security policy that can be enabled on the server side to
allow our front end to work with our REST API from a different origin**.

# The Data Layer

### SQL vs. NoSQL
**NoSQL databases** are better for big volumes of
records since these databases **are distributed**. We can deploy multiple nodes (or
instances), so they allow for good performance at writing data, reading it, or both. The
price we pay is that these databases follow the **CAP theorem**.

When we store data in a distributed way, we have to choose only **two
of the availability, consistency, and partition tolerance guarantees**. We normally want
partition tolerance since network errors will simply happen, so we should be able to
cope with them. Therefore, most of the time we have to choose between making the data
available as much time as possible or making it consistent.

**Relational databases (SQL)** follow the **ACID guarantees**:
**atomicity** (transactions either succeed or fail as a whole unit), **consistency** (data always
transitions between valid states), **isolation** (ensures that concurrency doesn’t cause side effects), and **durability** (after a transaction the state is persisted even in the event
of a system failure). Those are great features, but to ensure them, these databases can’t
deal properly with horizontal scalability (multiple distributed nodes), meaning they
don’t scale that well.

Analyze carefully what your data requirements are:

* How are you planning to query the data? 
* Do you need high availability?
* Are you writing millions of records? 
* Do you need very fast readings? 

### H2, Hibernate and JPA

* We’ll use the Spring Boot JPA annotations and integrations, so we keep our code decoupled from Hibernate
  specifics.
* On the implementation side, Hibernate takes care of all the logic to map our objects to database entities.
* Hibernate supports multiple SQL dialects for different databases, and the H2 dialect is one of them.
* Spring Boot autoconfiguration sets up H2 and Hibernate for us, but we can also customize behaviors.

This loose coupling between specifications and implementations gives us a big
  advantage: changing to a different database engine would be seamless since it’s
  abstracted by Hibernate and Spring Boot configuration.

### Spring Boot Data JPA
The Spring Framework has multiple modules available to work with databases, grouped
into the Spring Data family: 
* JDBC
* Cassandra
* Hadoop
* Elasticsearch, etc. 

One of them is **Spring Data JPA**, which **abstracts access to databases** using the Java Persistence API in a
Spring-based programming style. The starter for it is **spring-boot-starter-data-jpa** module.

#### Entities
Setting the fetch type to LAZY in the @ManyToOne annotation, the queries to retrieve those fields will be executed  only when
we try to access them. Otherwise, with fecth type as EAGER, the user data gets collected with the attempt.

This works because Hibernate configures proxy classes for the entity classes.
These proxy classes extended ours; that's why we shouldn't declare them **final** if we want this mechanism to work.
Hibernate will pass a proxy object that triggers the query to fetch the data needed only when the accessor (getter)
is used for the first time.
