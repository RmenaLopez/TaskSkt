package com.skt.finaltask.microservice.producers;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.microservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductProducer {

    private ProductRepository repository;

    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductProducer(final RabbitTemplate rabbitTemplate, ProductRepository productRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = productRepository;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessageToFront() {
        log.info("Sending list of products...");

        final List<Product> products = new ArrayList<>();

        // TODO: 26/05/18 Change findAll for getAllUser using stored proc.

        for (Product product: repository.findAll()){
            products.add(product);
        }

        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_FRONT_PRODUCTS, products);
    }
}
