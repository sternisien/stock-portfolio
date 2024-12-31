package org.example.application.port.output;

import java.util.List;
import org.example.domain.Stock;
import org.example.infrastructure.exception.ResourceNotFoundException;

public interface GetResourceStock {

  /**
   * Fonction permettant de récupérer l'ensemble des actions du portefeuille de l'utilisateur
   *
   * @param userId l'id de l'utilisateur recherché
   * @return La collection d'actions du portefeuille de l'utilisateur
   */
  List<Stock> getStocksPortfolioByUserId(Long userId);

  /**
   * Fonction permettant de récupérer une action spécifique dans un portefeuille utilisateur si elle
   * existe
   *
   * @param symbol le symbole de l'action recherché
   * @param userId l'id de l'utilisateur
   * @return l'action recherché si elle est trouvée
   */
  Stock getStockBySymbolInUserPortfolio(String symbol, Long userId)
      throws ResourceNotFoundException;
}
