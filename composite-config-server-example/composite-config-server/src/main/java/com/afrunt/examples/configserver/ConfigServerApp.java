package com.afrunt.examples.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * @author Andrii Frunt
 */
@EnableConfigServer
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ConfigServerApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServerApp.class);

    @Autowired
    private DataSource datasource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${spring.datasource.url}")
    private String h2Url;

    @PostConstruct
    public void initDatabase() throws SQLException {
        LOGGER.info(h2Url);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

        Supplier<Boolean> tableExistsSupplier = () -> {
            try {
                jdbcTemplate.queryForList("SELECT * FROM PROPERTIES");
                LOGGER.info("Table PROPERTIES exists");
                return true;
            } catch (Exception e) {
                LOGGER.info("Table PROPERTIES does not exist");
                return false;
            }
        };

        boolean tableExists = tableExistsSupplier.get();

        ScriptUtils.executeSqlScript(datasource.getConnection(), resourceLoader.getResource("classpath:create_properties.sql"));

        if (!tableExists) {
            LOGGER.info("Default properties will be imported");
            ScriptUtils.executeSqlScript(datasource.getConnection(), resourceLoader.getResource("classpath:insert_properties.sql"));
        }

        LOGGER.info("JDBC properties available");

        jdbcTemplate.query("SELECT * FROM PROPERTIES", rs -> {
            LOGGER.info("{}/{}:{}", rs.getString("APPLICATION"), rs.getString("KEY"), rs.getString("VALUE"));
        });

    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
