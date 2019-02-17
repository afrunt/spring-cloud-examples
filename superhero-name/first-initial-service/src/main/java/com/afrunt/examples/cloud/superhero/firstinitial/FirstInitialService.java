package com.afrunt.examples.cloud.superhero.firstinitial;

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
public class FirstInitialService {
    private Map<String, String> parts = Stream
            .of("Amazing", "Bombastic", "Chivalrous", "Daring", "Extraordinary", "Fantastic", "Gritty", "Helpful",
                    "Incredible", "Jaunty", "Killer", "Lowly", "Marvelous", "Nefarious", "Odious", "Poisonous", "Quixotic",
                    "Radioactive", "Savage", "Terrifying", "Unlikely", "Vicious", "Wild", "Xanthic", "Young", "Zealous")
            .collect(Collectors.toMap(v -> v.substring(0, 1).toUpperCase(), v -> v));

    @ResponseBody
    @GetMapping(value = "/part/{letter}", produces = "application/json")
    public Map<String, String> getPart(@PathVariable("letter") String letter) {
        if (System.currentTimeMillis() % 10 == 0) {
            throw new RuntimeException("Shit happens");
        }
        return Map.of("part", parts.get(letter.toUpperCase()));
    }
}
