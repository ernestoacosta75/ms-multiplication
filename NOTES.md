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
     the rest of the object to behave as the original implementation.