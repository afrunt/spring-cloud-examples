package com.afrunt.examples.cloud.superhero.web;

import feign.hystrix.FallbackFactory;
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
@FeignClient(name = "FirstInitialService", fallbackFactory = FirstInitialService.FirstInitialServiceFallbackFactory.class)
public interface FirstInitialService {
    @RequestMapping(value = "/part/{letter}", method = RequestMethod.GET, produces = "application/json")
    Map<String, String> part(@PathVariable("letter") String letter);

    @Component
    class FirstInitialServiceFallbackFactory implements FallbackFactory<FirstInitialService> {
        private static final Logger LOGGER = LoggerFactory.getLogger(FirstInitialServiceFallbackFactory.class);

        @Override
        public FirstInitialService create(Throwable throwable) {
            LOGGER.error("Unable to get first part of the name");
            return request -> Map.of("part", "Stupid");
        }
    }
}
