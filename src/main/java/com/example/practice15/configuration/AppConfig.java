package com.example.practice15.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@EnableJpaRepositories("com.example.practice15")
public class AppConfig {

}
