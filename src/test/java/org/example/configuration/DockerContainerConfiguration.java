package org.example.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class DockerContainerConfiguration {

  @Bean
  public PostgreSQLContainer<?> getPosgresSqlContainer() {
    return new PostgreSQLContainer<>("postgres:15.3")
        .withDatabaseName("portfolio")
        .withUsername("test")
        .withPassword("test")
        .withInitScripts("sql/stock/init_db.sql")
        .withReuse(false);
  }
}
