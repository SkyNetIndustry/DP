package io.smart.recuperation.recuperation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.smart.recuperation.recuperation.domain")
@EnableJpaRepositories("io.smart.recuperation.recuperation.repos")
@EnableTransactionManagement
public class DomainConfig {
}
