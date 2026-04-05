package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the MapStruct Learning Lab.
 *
 * <p>Spring Boot auto-configures the web server (Tomcat on port 8086) and
 * performs component scanning from this package downward, which picks up all
 * {@code @RestController}, {@code @Component}, and MapStruct
 * {@code @Mapper(componentModel = "spring")} beans automatically.</p>
 */
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
