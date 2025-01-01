package org.example.stock.unit_test.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.example.application.dto.BalancePortfolioDto;
import org.example.application.mapper.portfolio.PortfolioDomainMapper;
import org.example.application.port.output.GetResourcePortfolio;
import org.example.application.service.GetDataAssetPortfolioService;
import org.example.domain.Portfolio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetDataAssetPortfolioServiceTest {

  @Spy
  private PortfolioDomainMapper portfolioDomainMapper =
      Mappers.getMapper(PortfolioDomainMapper.class);

  @Mock private GetResourcePortfolio resourcePortfolio;

  @InjectMocks private GetDataAssetPortfolioService portfolioService;

  @Test
  void get_balance_porfolio_user_id_null() {

    Assertions.assertThrows(
        RuntimeException.class, () -> portfolioService.getBalanceUserPortfolio(null));
  }

  @Test
  void get_balance_portfolio_user_ok() {
    Portfolio userPortfolio = new Portfolio(1L, 2500, 1000, LocalDateTime.now(), 1L, List.of());

    Mockito.when(resourcePortfolio.getUserPortfolio(1L)).thenReturn(userPortfolio);
    BalancePortfolioDto balancePortfolioDto = portfolioService.getBalanceUserPortfolio(1L);

    Assertions.assertAll(
        () -> assertEquals(2500d, balancePortfolioDto.amountFundsAvailable()),
        () -> assertEquals(1000d, balancePortfolioDto.amountFundsLocked()));
  }
}
