package org.example.stock.integration_test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.example.configuration.DockerContainerConfiguration;
import org.example.infrastructure.exception.message.PortfolioMessageException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Import(DockerContainerConfiguration.class)
@Sql(
    value = "classpath:sql/stock/stock_jdd.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class PorfolioControllerTest {

  @Container @Autowired private PostgreSQLContainer<?> postgreSQLContainer;

  @Autowired private MockMvc mockMvc;

  @Test
  void get_balance_portfolio_404_not_exist() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get("/portfolios/{userId}/balance", 2))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(
            jsonPath("$.message")
                .value(String.format(PortfolioMessageException.USER_PORTFOLIO_NOT_FOUND, 2)))
        .andExpect(jsonPath("$.httpStatusCode").value("404"))
        .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
        .andExpect(jsonPath("$.resourceType").value("Portfolio"))
        .andReturn();
  }

  @Test
  void get_balance_portfolio_200_ok() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get("/portfolios/{userId}/balance", 1))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.amountFundsAvailable").value(2000))
        .andExpect(jsonPath("$.amountFundsLocked").value(0))
        .andReturn();
  }
}
