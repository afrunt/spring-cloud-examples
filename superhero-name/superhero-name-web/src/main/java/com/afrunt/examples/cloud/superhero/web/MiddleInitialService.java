package com.afrunt.examples.cloud.superhero.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Andrii Frunt
 */
@Service
public class MiddleInitialService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiddleInitialService.class);

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @HystrixCommand(commandKey = "MiddleInitialService#part(Map)", groupKey = "MiddleInitialService", fallbackMethod = "partFallback")
    public Map<String, String> part(String letter) {

        return restTemplate
                .getForEntity("http://MiddleInitialService/part/" + letter, Map.class)
                .getBody();
    }

    public Map<String, String> partFallback(String letter, Throwable ex) {
        LOGGER.error("Fuck, cannot retrieve the middle part of the name");
        return Map.of("part", "Jumping");
    }

}
