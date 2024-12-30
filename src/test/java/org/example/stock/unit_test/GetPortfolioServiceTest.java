package org.example.stock.unit_test;

import java.time.LocalDateTime;
import java.util.List;
import org.example.application.dto.StockPortfolioDto;
import org.example.application.mapper.stock.StockDomainMapper;
import org.example.application.port.out.GetResourceStock;
import org.example.application.service.GetStockUserPortolioService;
import org.example.domain.Stock;
import org.example.infrastructure.exception.message.StockMessageException;
import org.example.infrastructure.exception.message.UserMessageException;
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
public class GetPortfolioServiceTest {

  private static final LocalDateTime LAST_UPDATED = LocalDateTime.now();

  @Spy private StockDomainMapper stockDomainMapper = Mappers.getMapper(StockDomainMapper.class);
  @Mock private GetResourceStock stockRetreiverAdapter;

  @InjectMocks private GetStockUserPortolioService portfolioUserService;

  @Test
  void get_user_portfolio_user_id_null() {

    RuntimeException err =
        Assertions.assertThrows(
            RuntimeException.class, () -> portfolioUserService.getStocksInUserPortfolio(null));

    Assertions.assertEquals(UserMessageException.USER_ID_NOT_PROVIDED, err.getMessage());
  }

  @Test
  void get_user_portfolio_stocks() {
    Stock appleStock = new Stock(1L, "AAPL", 10, 115, LAST_UPDATED, 1L);
    Stock teslaStock = new Stock(1L, "TSLA", 15, 300, LAST_UPDATED, 1L);

    Mockito.when(stockRetreiverAdapter.getStocksPortfolioByUserId(1L))
        .thenReturn(List.of(appleStock, teslaStock));

    List<StockPortfolioDto> resultat = portfolioUserService.getStocksInUserPortfolio(1L);
    Assertions.assertEquals(2, resultat.size());
    Assertions.assertAll(() -> Assertions.assertEquals(2, resultat.size()));

    StockPortfolioDto appleStockDto = resultat.get(0);
    StockPortfolioDto teslaStockDto = resultat.get(1);

    Assertions.assertAll(
        () -> Assertions.assertEquals("AAPL", appleStockDto.symbol()),
        () -> Assertions.assertEquals(115d, appleStockDto.price()),
        () -> Assertions.assertEquals(10, appleStockDto.quantity()),
        () -> Assertions.assertEquals("TSLA", teslaStockDto.symbol()),
        () -> Assertions.assertEquals(300d, teslaStockDto.price()),
        () -> Assertions.assertEquals(15, teslaStockDto.quantity()));
  }

  @Test
  void get_user_portfolio_no_stocks() {
    Stock appleStock = new Stock(1L, "AAPL", 10, 115, LAST_UPDATED, 1L);
    Stock teslaStock = new Stock(1L, "TSLA", 15, 300, LAST_UPDATED, 1L);

    Mockito.when(stockRetreiverAdapter.getStocksPortfolioByUserId(1L)).thenReturn(List.of());

    List<StockPortfolioDto> resultat = portfolioUserService.getStocksInUserPortfolio(1L);
    Assertions.assertEquals(0, resultat.size());
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_ko_user_null() {

    RuntimeException err =
        Assertions.assertThrows(
            RuntimeException.class,
            () -> portfolioUserService.getStockBySymbolInUserPortfolio("AAPL", null));

    Assertions.assertEquals(UserMessageException.USER_ID_NOT_PROVIDED, err.getMessage());
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_ko_symbol_null() {

    RuntimeException err =
        Assertions.assertThrows(
            RuntimeException.class,
            () -> portfolioUserService.getStockBySymbolInUserPortfolio(null, 1L));

    Assertions.assertEquals(StockMessageException.STOCK_SYMBOL_NOT_PROVIDED, err.getMessage());
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_ko_symbol_empty() {

    RuntimeException err =
        Assertions.assertThrows(
            RuntimeException.class,
            () -> portfolioUserService.getStockBySymbolInUserPortfolio(" ", 1L));

    Assertions.assertEquals(StockMessageException.STOCK_SYMBOL_NOT_PROVIDED, err.getMessage());
  }

  @Test
  void get_stock_by_symbol_in_user_portfolio_ok() {
    Stock appleStock = new Stock(1L, "AAPL", 10, 115, LAST_UPDATED, 1L);

    Mockito.when(stockRetreiverAdapter.getStockBySymbolInUserPortfolio("AAPL", 1L))
        .thenReturn(appleStock);

    StockPortfolioDto appleStockDto =
        portfolioUserService.getStockBySymbolInUserPortfolio("AAPL", 1L);
    Assertions.assertAll(
        () -> Assertions.assertEquals("AAPL", appleStockDto.symbol()),
        () -> Assertions.assertEquals(115d, appleStockDto.price()),
        () -> Assertions.assertEquals(10, appleStockDto.quantity()));
  }
}
