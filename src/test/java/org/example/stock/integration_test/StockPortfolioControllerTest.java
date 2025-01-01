package org.example.stock.integration_test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.configuration.DockerContainerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Import(DockerContainerConfiguration.class)
@Sql(
    value = {"classpath:sql/stock/stock_jdd.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(
    value = {"classpath:sql/stock/common/clean_db.sql"},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class StockPortfolioControllerTest {

  @Autowired @Container private PostgreSQLContainer<?> postgreSQLContainer;
  @Autowired private MockMvc mockMvc;

  @Test
  void get_user_portfolio_ok() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/portfolios/{userId}/stocks", 1)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$.[0].symbol").value("AAPL"))
        .andExpect(jsonPath("$.[0].quantity").value("50"))
        .andExpect(jsonPath("$.[0].price").value("150.25"))
        .andExpect(jsonPath("$.[0].lastUpdated").value("2024-12-27T10:00:00"))
        .andExpect(jsonPath("$.[1].symbol").value("GOOGL"))
        .andExpect(jsonPath("$.[1].quantity").value("10"))
        .andExpect(jsonPath("$.[1].price").value("2800.5"))
        .andExpect(jsonPath("$.[1].lastUpdated").value("2024-12-27T10:05:00"))
        .andExpect(jsonPath("$.[2].symbol").value("TSLA"))
        .andExpect(jsonPath("$.[2].quantity").value("20"))
        .andExpect(jsonPath("$.[2].price").value("700.0"))
        .andExpect(jsonPath("$.[2].lastUpdated").value("2024-12-27T10:15:00"))
        .andReturn();
  }

  @Test
  void get_user_portfolio_no_stocks() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/portfolios/{userId}/stocks", 2)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(0))
        .andReturn();
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_ok() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/portfolios/{userId}/stocks/{symbol}", 1, "TSLA")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.quantity").value("20"))
        .andExpect(jsonPath("$.price").value("700.0"))
        .andExpect(jsonPath("$.lastUpdated").value("2024-12-27T10:15:00"))
        .andExpect(jsonPath("$.symbol").value("TSLA"))
        .andReturn();
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_symbol_not_found() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/portfolios/{userId}/stocks/{symbol}", 1, "MSF")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(
            jsonPath("$.message")
                .value(
                    "L'action MSF recherchée dans le portefeuille de l'utilisateur 1 n'a pas été trouvé"))
        .andExpect(jsonPath("$.httpStatusCode").value("404"))
        .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
        .andExpect(jsonPath("$.resourceType").value("Stock"))
        .andReturn();
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_user_no_portfolio() throws Exception {

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/portfolios/{userId}/stocks/{symbol}", 2, "AAPL")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(
            jsonPath("$.message")
                .value(
                    "L'action AAPL recherchée dans le portefeuille de l'utilisateur 2 n'a pas été trouvé"))
        .andExpect(jsonPath("$.httpStatusCode").value("404"))
        .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
        .andExpect(jsonPath("$.resourceType").value("Stock"))
        .andReturn();
  }
}
