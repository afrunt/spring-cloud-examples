package com.afrunt.examples.cloud.superhero.lastinitial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Andrii Frunt
 */
@RestController
public class LastInitialService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LastInitialService.class);
    private Map<String, String> parts = Stream
            .of(
                    "Alien", "Beast", "Corpse", "Dragon", "Eagle", "Fairy", "Giant", "Hawk", "Imp", "Jaguar", "Kid",
                    "Lizard", "Man/Woman", "Nymph", "Ogre", "Python", "Queen", "Robot", "Spirit", "Thief", "Underdog",
                    "Vampire", "X-Ray", "Youth", "Zombie"
            )
            .collect(Collectors.toMap(v -> v.substring(0, 1).toUpperCase(), v -> v));

    @ResponseBody
    @GetMapping(value = "/part/{letter}", produces = "application/json")
    public Map<String, String> getPart(@PathVariable("letter") String letter) {
        if (System.currentTimeMillis() % 10 == 0) {
            throw new RuntimeException("Boom! Cannot connect to database");
        }
        String part = parts.get(letter.toUpperCase());
        LOGGER.info("{} -> {}", letter, part);
        return Map.of("part", part);
    }
}
