package microservices.book.multiplication.infrastructure.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configures Rabbit MQ via AMQP (Advanced Message Queuing Protocol) abstraction to use events
 * in the application.
 */
@Configuration
public class AMQPConfig {

    /**
     * Declares a topic exchange to send messages related to challenge attempts.
     * It will be durable, meaning it will survive broker restarts.
     * @param exchangeName the name of the exchange, injected from application properties
     * @return the configured TopicExchange
     */
    @Bean
    public TopicExchange challengesTopicExchange(@Value("${amqp.exchange.attempts}") final String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    /**
     * Configures a message converter to serialize and deserialize messages to/from JSON format.
     * This allows for easy integration with other services that consume or produce JSON messages.
     * This bean will be used by the RabbitTemplate implementation, the class that serializes and send
     * objects as AMQP messages to the broker.
     * @return the Jackson2JsonMessageConverter
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
