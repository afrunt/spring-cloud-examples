package com.afrunt.examples.cloud.fibonacci.simple.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author Andrii Frunt
 */
@ComponentScan("com.afrunt.examples.cloud.service.fibonacci")
@SpringBootApplication
public class FibonacciSimpleApp {
    public static void main(String[] args) {
        SpringApplication.run(FibonacciSimpleApp.class, args);
    }
}
