package com.bandrolling.bandrolling.config;

import com.bandrolling.bandrolling.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

@Configuration
public class SeedDataConfig {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 100; i++) {
                var user = new com.bandrolling.bandrolling.entity.User(
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.date().birthday().toInstant(),
                        faker.date().birthday().toInstant()
                );
                userRepository.save(user);
            }
        };
    }
}
