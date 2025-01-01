package org.example.application.service;

import java.util.List;
import java.util.Objects;
import org.example.application.dto.StockPortfolioDto;
import org.example.application.mapper.stock.StockDomainMapper;
import org.example.application.port.in.GetStockUserPortfolioUseCase;
import org.example.application.port.output.GetResourceStock;
import org.example.domain.Stock;
import org.example.infrastructure.exception.DataValidationException;
import org.example.infrastructure.exception.message.StockMessageException;
import org.example.infrastructure.exception.message.UserMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class GetStockUserPortolioService implements GetStockUserPortfolioUseCase {

  private final GetResourceStock stockRetreiverAdapter;

  private final StockDomainMapper stockDomainMapper;

  public GetStockUserPortolioService(
      GetResourceStock stockRetreiverAdapter, StockDomainMapper stockDomainMapper) {
    this.stockRetreiverAdapter = stockRetreiverAdapter;
    this.stockDomainMapper = stockDomainMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public List<StockPortfolioDto> getStocksInUserPortfolio(Long userId) {
    if (Objects.isNull(userId)) {
      throw new DataValidationException(
          UserMessageException.USER_ID_NOT_PROVIDED, Stock.class.getSimpleName());
    }

    return stockRetreiverAdapter.getStocksPortfolioByUserId(userId).stream()
        .map(stockDomainMapper::domainToStockPortolioDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public StockPortfolioDto getStockBySymbolInUserPortfolio(String stockSymbol, Long userId) {
    if (Objects.isNull(userId)) {
      throw new DataValidationException(
          UserMessageException.USER_ID_NOT_PROVIDED, Stock.class.getSimpleName());
    }

    if (!StringUtils.hasText(stockSymbol)) {
      throw new DataValidationException(
          StockMessageException.STOCK_SYMBOL_NOT_PROVIDED, Stock.class.getSimpleName());
    }

    return stockDomainMapper.domainToStockPortolioDto(
        stockRetreiverAdapter.getStockBySymbolInUserPortfolio(stockSymbol, userId));
  }
}
