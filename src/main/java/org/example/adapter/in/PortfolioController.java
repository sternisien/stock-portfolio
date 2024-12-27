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
public class PortfolioController {

  private final GetStockUserPortfolioUseCase portfolioUserUseCase;

  public PortfolioController(GetStockUserPortfolioUseCase portfolioUserUseCase) {
    this.portfolioUserUseCase = portfolioUserUseCase;
  }

  @GetMapping("/{userId}/stocks")
  public List<StockPortfolioDto> getStocksPortfolioForUser(@PathVariable Long userId) {
    return portfolioUserUseCase.getStocksInUserPortfolio(userId);
  }

  @GetMapping("/{userId}/stocks/{symbol}")
  public StockPortfolioDto getStockBySymbolForUser(
      @PathVariable Long userId, @PathVariable String symbol) {
    return portfolioUserUseCase.getStockBySymbolInUserPortfolio(symbol, userId);
  }
}
