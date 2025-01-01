package org.example.application.service;

import java.util.Objects;
import org.example.application.dto.BalancePortfolioDto;
import org.example.application.mapper.portfolio.PortfolioDomainMapper;
import org.example.application.port.in.GetDataAssetPortfolioUseCase;
import org.example.application.port.output.GetResourcePortfolio;
import org.example.domain.Portfolio;
import org.example.infrastructure.exception.DataValidationException;
import org.example.infrastructure.exception.message.UserMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetDataAssetPortfolioService implements GetDataAssetPortfolioUseCase {

  private final GetResourcePortfolio resourcePortfolio;

  private final PortfolioDomainMapper portfolioDomainMapper;

  public GetDataAssetPortfolioService(
      GetResourcePortfolio resourcePortfolio, PortfolioDomainMapper portfolioDomainMapper) {
    this.resourcePortfolio = resourcePortfolio;
    this.portfolioDomainMapper = portfolioDomainMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public BalancePortfolioDto getBalanceUserPortfolio(Long userId) {
    if (Objects.isNull(userId)) {
      throw new DataValidationException(
          UserMessageException.USER_ID_NOT_PROVIDED, Portfolio.class.getSimpleName());
    }
    return portfolioDomainMapper.mapToBalancePortfolio(resourcePortfolio.getUserPortfolio(userId));
  }
}
