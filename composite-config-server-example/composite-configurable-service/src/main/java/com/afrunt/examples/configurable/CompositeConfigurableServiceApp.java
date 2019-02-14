package com.afrunt.examples.configurable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Andrii Frunt
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class CompositeConfigurableServiceApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeConfigurableServiceApp.class);
    @Autowired
    private ContextRefresher contextRefresher;

    //@Async
    //@Scheduled(fixedDelay = 5000)
    public void scheduledRefresh() {
        contextRefresher.refresh();
        LOGGER.info("Scheduled refresh completed");
    }

    public static void main(String[] args) {
        SpringApplication.run(CompositeConfigurableServiceApp.class, args);
    }
}
