package org.example.adapter.output;

import org.example.adapter.output.repository.PortfolioRepository;
import org.example.application.mapper.portfolio.PortfolioEntityMapper;
import org.example.application.port.output.GetResourcePortfolio;
import org.example.domain.Portfolio;
import org.example.infrastructure.exception.ResourceNotFoundException;
import org.example.infrastructure.exception.message.PortfolioMessageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PortfolioRetrieverAdapter implements GetResourcePortfolio {

  private final PortfolioRepository portfolioRepository;

  private final PortfolioEntityMapper portfolioEntityMapper;

  public PortfolioRetrieverAdapter(
      PortfolioRepository portfolioRepository, PortfolioEntityMapper portfolioEntityMapper) {
    this.portfolioRepository = portfolioRepository;
    this.portfolioEntityMapper = portfolioEntityMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public Portfolio getUserPortfolio(Long userId) {
    return portfolioRepository
        .findByUserId(userId)
        .map(portfolioEntityMapper::toDomain)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format(PortfolioMessageException.USER_PORTFOLIO_NOT_FOUND, userId),
                    Portfolio.class.getSimpleName()));
  }
}
