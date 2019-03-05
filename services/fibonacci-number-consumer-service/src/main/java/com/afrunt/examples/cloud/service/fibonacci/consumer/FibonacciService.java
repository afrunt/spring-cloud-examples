package com.afrunt.examples.cloud.service.fibonacci.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Andrii Frunt
 */
@FeignClient(name = "FibonacciNumberService")
public interface FibonacciService {
    @RequestMapping(value = "/fibonacci/{n}", method = RequestMethod.GET, produces = "application/json")
    Map<String, Object> get(@PathVariable("n") int n);
}
