package com.skt.finaltask.commonLibrary.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@EnableRabbit
public class RabbitConfiguration implements RabbitListenerConfigurer {


    public static final String EXCHANGE_NAME = "appExchange";

    public static final String QUEUE_USERS_TO_FRONT = "appQueueToFrontUsers";
    public static final String QUEUE_USERS_TO_DB = "appQueueToDBUsers";
    public static final String ROUTING_KEY_TO_FRONT_USERS = "usersToFront.key";
    public static final String ROUTING_KEY_TO_DB_USERS = "usersToDB.key";

    public static final String QUEUE_PRODUCTS_TO_FRONT = "appQueueToFrontProducts";
    public static final String QUEUE_PRODUCTS_TO_DB = "appQueueToDBProducts";
    public static final String ROUTING_KEY_TO_FRONT_PRODUCTS = "productsToFront.key";
    public static final String ROUTING_KEY_TO_DB_PRODUCTS = "productsToDB.key";



    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueToFrontUsers() {
        return new Queue(QUEUE_USERS_TO_FRONT);
    }

    @Bean
    public Queue appQueueToDBUsers() { return new Queue(QUEUE_USERS_TO_DB); }

    @Bean
    public Queue appQueueToFrontProducts(){ return new Queue(QUEUE_PRODUCTS_TO_FRONT); }

    @Bean
    public Queue appQueueToDBProducts(){ return new Queue(QUEUE_PRODUCTS_TO_DB); }

    @Bean
    public Binding declareBindingToFrontUsers() {
        return BindingBuilder.bind(appQueueToFrontUsers()).to(appExchange()).with(ROUTING_KEY_TO_FRONT_USERS);
    }

    @Bean
    public Binding declareBindingToDBUsers() {
        return BindingBuilder.bind(appQueueToDBUsers()).to(appExchange()).with(ROUTING_KEY_TO_DB_USERS);
    }

    @Bean
    public Binding declareBindingToFrontProducts() {
        return BindingBuilder.bind(appQueueToFrontProducts()).to(appExchange()).with(ROUTING_KEY_TO_FRONT_PRODUCTS);
    }

    @Bean
    public Binding declareBindingToDBProducts() {
        return BindingBuilder.bind(appQueueToDBProducts()).to(appExchange()).with(ROUTING_KEY_TO_DB_PRODUCTS);
    }

    // You can comment all methods below and remove interface's implementation to use the default serialization / deserialization
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
