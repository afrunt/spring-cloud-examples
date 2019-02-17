package com.afrunt.examples.cloud.superhero.middleinitial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Map.entry;

/**
 * @author Andrii Frunt
 */
@RestController
public class MiddleInitialService {
    private Map<String, String> parts = Map.ofEntries(
            entry("A", "Flying"),
            entry("B", "Growing"),
            entry("C", "Furry"),
            entry("D", "Indestructible"),
            entry("E", "Raging"),
            entry("F", "Mind-Reading"),
            entry("G", "Shrinking"),
            entry("H", "Invisible"),
            entry("I", "Steel"),
            entry("J", "Frozen"),
            entry("K", "Fiery"),
            entry("L", "Green"),
            entry("M", "Silver"),
            entry("N", "Evil"),
            entry("O", "Fighting"),
            entry("P", "Yellow"),
            entry("Q", "Speedy"),
            entry("R", "Swimming"),
            entry("S", "Levitating"),
            entry("T", "Red"),
            entry("U", "Lazer"),
            entry("V", "Exploding"),
            entry("W", "Armored"),
            entry("X", "Blue"),
            entry("Y", "Underground"),
            entry("Z", "Orange")
    );

    @ResponseBody
    @GetMapping(value = "/part/{letter}", produces = "application/json")
    public Map<String, String> getPart(@PathVariable("letter") String letter) {
        if (System.currentTimeMillis() % 10 == 0) {
            throw new RuntimeException("Damn! Zombie apocalypse is here");
        }
        return Map.of("part", parts.get(letter.toUpperCase()));
    }
}
