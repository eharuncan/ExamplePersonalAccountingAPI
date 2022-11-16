package com.personalaccounting.api.db;

import com.personalaccounting.api.domain.User;
import com.personalaccounting.api.repositories.UserRepository;
import com.personalaccounting.api.enums.UserTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User(UserTypes.ADMIN, "admin", "admin", "admin", "admin")));
            log.info("Preloading " + repository.save(new User(UserTypes.CUSTOMER, "customer1", "customer1", "1", "1")));
        };
    }
}
