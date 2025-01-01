package org.example.application.port.in;

import org.example.application.dto.BalancePortfolioDto;

public interface GetDataAssetPortfolioUseCase {

  BalancePortfolioDto getBalanceUserPortfolio(Long userId);
}
