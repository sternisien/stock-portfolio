package org.example.application.port.in;

import java.util.List;
import org.example.application.dto.StockPortfolioDto;

public interface GetStockUserPortfolioUseCase {

  List<StockPortfolioDto> getStocksInUserPortfolio(Long userId);

  StockPortfolioDto getStockBySymbolInUserPortfolio(String stockSymbol, Long userId);
}
