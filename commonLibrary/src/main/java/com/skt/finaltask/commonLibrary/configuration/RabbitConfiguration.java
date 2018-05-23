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
    public static final String QUEUE_USER_TO_FRONT= "appQueueToFront";
    public static final String QUEUE_USER_TO_DB = "appQueueToDB";
    public static final String ROUTING_KEY_TO_FRONT = "usersToFront.key";
    public static final String ROUTING_KEY_TO_DB = "usersToDB.key";



    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean("Queue_To_Front")
    public Queue appQueueToFront() {
        return new Queue(QUEUE_USER_TO_FRONT);
    }

    @Bean("Queue_To_DB")
    public Queue appQueueToDB() { return new Queue(QUEUE_USER_TO_DB); }

    @Bean
    public Binding declareBindingToFront() {
        return BindingBuilder.bind(appQueueToFront()).to(appExchange()).with(ROUTING_KEY_TO_FRONT);
    }

    @Bean
    public Binding declareBindingToDB() {
        return BindingBuilder.bind(appQueueToDB()).to(appExchange()).with(ROUTING_KEY_TO_DB);
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
