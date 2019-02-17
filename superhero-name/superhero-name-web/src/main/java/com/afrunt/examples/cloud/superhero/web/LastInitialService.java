package com.afrunt.examples.cloud.superhero.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Andrii Frunt
 */
@FeignClient(name = "LastInitialService", fallback = LastInitialService.LastInitialServiceFallback.class)
public interface LastInitialService {
    @RequestMapping(value = "/part/{letter}", method = RequestMethod.GET, produces = "application/json")
    Map<String, String> part(@PathVariable("letter") String letter);

    @Component
    class LastInitialServiceFallback implements LastInitialService {
        public static final Logger LOGGER = LoggerFactory.getLogger(LastInitialService.class);

        @Override
        public Map<String, String> part(String letter) {
            LOGGER.error("Cannot get the last part of the name");
            return Map.of("part", "Skunk");
        }
    }
}
