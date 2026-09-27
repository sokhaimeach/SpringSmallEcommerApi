package org.backend.smallecommerceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmallEcommerceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmallEcommerceApiApplication.class, args);
    }

}
