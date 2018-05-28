package com.skt.finaltask.managementApp.managementApp.service;


import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final RabbitTemplate rabbitTemplate;
    private List<Product> productList;

    @Autowired
    public ProductService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addProduct(Product product) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_DB_PRODUCTS, product);
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_PRODUCTS_TO_FRONT)
    private void getUsersFromDB(final List<Product> products){
        List<Product> productList= new ArrayList<>();
        for (Product product : products){
            productList.add(new Product(product.getDescription(), product.getPrice(), product.getBrand()));
        }
        this.productList = productList;
    }

    public List<Product> getProducts(){
        return productList;
    }

}
