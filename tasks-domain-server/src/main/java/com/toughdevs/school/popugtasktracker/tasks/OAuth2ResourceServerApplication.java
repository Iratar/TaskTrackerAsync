package com.toughdevs.school.popugtasktracker.tasks;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBinding(Processor.class)
//The @EnableSchemaRegistryClient annotation needs to be uncommented to use the Spring native method.
//@EnableSchemaRegistryClient
public class OAuth2ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ResourceServerApplication.class, args);
    }

}
