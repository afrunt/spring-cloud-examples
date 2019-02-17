package com.afrunt.examples.cloud.superhero.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andrii Frunt
 */
@Controller
public class SuperheroNameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperheroNameController.class);

    @Autowired
    private SuperheroNameService superheroNameService;

    @Value("${hystrix.host:localhost}")
    private String hystrixHost;

    @Value("${server.port:8080}")
    private Integer port;

    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String eurekaUrl;

    @GetMapping(value = {"", "/"})
    public String index(Model model) {
        char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


        model.addAttribute("alphabet", alphabetArray);
        return "index";
    }

    @GetMapping("/superhero-name")
    public String postSuperHeroForm(
            @RequestParam(value = "firstInitial") String firstInitial,
            @RequestParam(value = "middleInitial") String middleInitial,
            @RequestParam(value = "lastInitial") String lastInitial,
            Model model
    ) {
        LOGGER.info("Letters: {} {} {}", firstInitial, middleInitial, lastInitial);

        String name = superheroNameService.chooseName(firstInitial, middleInitial, lastInitial);

        LOGGER.info("Name is: {}", name);

        model.addAttribute("name", name);

        String hystrixLocalUrlPart = "http://" + hystrixHost + ":" + port + "/actuator/hystrix.stream";

        String hystrixStreamUrl = "/hystrix/monitor?stream=" + hystrixLocalUrlPart;
        model.addAttribute("hystrixStreamUrl", hystrixStreamUrl);
        model.addAttribute("eurekaUrl", eurekaUrl.replace("/eureka", ""));

        return "name";
    }


}
