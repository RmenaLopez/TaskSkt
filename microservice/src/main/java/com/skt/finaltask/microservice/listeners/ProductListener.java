package com.skt.finaltask.microservice.listeners;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.microservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductListener {
    private static final Logger log = LoggerFactory.getLogger(ProductListener.class);

    @Autowired
    private ProductRepository repository;

    @RabbitListener(queues = RabbitConfiguration.QUEUE_PRODUCTS_TO_DB)
    public void receiveUserToDB(final Product product) {
        log.info("Received Product TO DB: {}", product.getDescription());

        repository.addProduct(product.getDescription(),
                product.getPrice(),
                product.getBrand());
    }
}
