package org.example.application.mapper.portfolio;

import org.example.application.dto.BalancePortfolioDto;
import org.example.domain.Portfolio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioDomainMapper {

  @Mapping(source = "availableFunds", target = "amountFundsAvailable")
  @Mapping(source = "fundsLocked", target = "amountFundsLocked")
  BalancePortfolioDto mapToBalancePortfolio(Portfolio portfolio);
}
