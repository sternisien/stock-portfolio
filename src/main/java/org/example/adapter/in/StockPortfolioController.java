package org.example.adapter.in;

import java.util.List;
import org.example.application.dto.StockPortfolioDto;
import org.example.application.port.in.GetStockUserPortfolioUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolios")
public class StockPortfolioController {

  private final GetStockUserPortfolioUseCase stockUserPortfolioUseCase;

  public StockPortfolioController(GetStockUserPortfolioUseCase portfolioUserUseCase) {
    this.stockUserPortfolioUseCase = portfolioUserUseCase;
  }

  @GetMapping("/{userId}/stocks")
  public List<StockPortfolioDto> getStocksPortfolioForUser(@PathVariable Long userId) {
    return stockUserPortfolioUseCase.getStocksInUserPortfolio(userId);
  }

  @GetMapping("/{userId}/stocks/{symbol}")
  public StockPortfolioDto getStockBySymbolForUser(
      @PathVariable Long userId, @PathVariable String symbol) {
    return stockUserPortfolioUseCase.getStockBySymbolInUserPortfolio(symbol, userId);
  }
}
