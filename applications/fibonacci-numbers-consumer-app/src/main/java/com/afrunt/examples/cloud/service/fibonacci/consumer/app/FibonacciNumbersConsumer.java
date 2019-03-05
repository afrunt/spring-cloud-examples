package com.afrunt.examples.cloud.service.fibonacci.consumer.app;

import com.afrunt.examples.cloud.service.fibonacci.consumer.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Andrii Frunt
 */
@EnableFeignClients(basePackages = "com.afrunt.examples.cloud.service.fibonacci.consumer")
@SpringBootApplication
public class FibonacciNumbersConsumer {
    @Autowired
    private FibonacciService fibonacciService;

    @PostConstruct
    public void init() {
        Map<String, Object> stringObjectMap = fibonacciService.get(0);
        System.out.println();
    }

    public static void main(String[] args) {
        SpringApplication.run(FibonacciNumbersConsumer.class, args);
    }
}
