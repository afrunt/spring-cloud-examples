package com.afrunt.examples.configurable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Andrii Frunt
 */
@RestController
@RefreshScope
public class ConfigurableController {
    @Value("${someSetting}")
    private String someSetting;

    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
                "someSetting", someSetting
        );
    }
}
