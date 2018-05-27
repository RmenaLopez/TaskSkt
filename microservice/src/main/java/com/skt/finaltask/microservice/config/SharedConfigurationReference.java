package com.skt.finaltask.microservice.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.skt.finaltask.commonLibrary.configuration")
@EntityScan("com.skt.finaltask.commonLibrary.model")
public class SharedConfigurationReference {
}
