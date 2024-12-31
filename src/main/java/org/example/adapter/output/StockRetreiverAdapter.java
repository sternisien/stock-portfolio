package org.example.adapter.output;

import java.util.List;
import org.example.adapter.output.repository.StockRepository;
import org.example.application.mapper.stock.StockEntityMapper;
import org.example.application.port.output.GetResourceStock;
import org.example.domain.Stock;
import org.example.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StockRetreiverAdapter implements GetResourceStock {

  private final StockRepository stockRepository;
  private final StockEntityMapper stockEntityMapper;

  public StockRetreiverAdapter(
      StockRepository stockRepository, StockEntityMapper stockEntityMapper) {
    this.stockRepository = stockRepository;
    this.stockEntityMapper = stockEntityMapper;
  }

  @Override
  public List<Stock> getStocksPortfolioByUserId(Long userId) {
    return stockRepository.findByPortfolioUserIdOrderBySymbolAsc(userId).stream()
        .map(stockEntityMapper::map)
        .toList();
  }

  @Override
  public Stock getStockBySymbolInUserPortfolio(String symbol, Long userId)
      throws ResourceNotFoundException {
    return stockRepository
        .findByPortfolioUserIdAndSymbolIgnoreCase(userId, symbol)
        .map(stockEntityMapper::map)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format(
                        "L'action %s recherchée dans le portefeuille de l'utilisateur %s n'a pas été trouvé",
                        symbol, userId),
                    Stock.class.getSimpleName()));
  }
}
