package com.personalaccounting.api.db;

import com.personalaccounting.api.dtos.UserRegisterDto;
import com.personalaccounting.api.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            log.info("Preloading " + userService.register(new UserRegisterDto("admin", "admin", "admin", "admin")));
            log.info("Preloading " + userService.register(new UserRegisterDto("customer1", "customer1", "1", "1")));
        };
    }
}
