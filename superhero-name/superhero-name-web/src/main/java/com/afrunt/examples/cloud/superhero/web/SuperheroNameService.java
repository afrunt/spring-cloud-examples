package com.afrunt.examples.cloud.superhero.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Frunt
 */
@Service
public class SuperheroNameService {
    @Autowired
    private FirstInitialService firstInitialService;

    @Autowired
    private MiddleInitialService middleInitialService;

    @Autowired
    private LastInitialService lastInitialService;

    public String chooseName(String firstInitial, String middleInitial, String lastInitial) {
        String firstPart = firstInitialService.part(firstInitial).get("part");
        String middlePart = middleInitialService.part(middleInitial).get("part");
        String lastPart = lastInitialService.part(lastInitial).get("part");

        return firstPart + " " + middlePart + " " + lastPart;
    }
}
