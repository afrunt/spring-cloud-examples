package com.afrunt.examples.cloud.service.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Andrii Frunt
 */
@RestController
public class FibonacciService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciService.class);

    @GetMapping(value = "/fibonacci/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> get(@PathVariable("n") int n) {
        int number = calculate(n);

        LOGGER.info("Fibonacci's number {} -> {}", n, number);

        return Map.of("number", number);
    }

    private int calculate(int n) {
        int previousNumber = 0;
        int nextNumber = 1;

        for (int i = 0; i < n; i++) {
            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }

        return previousNumber;
    }
}
