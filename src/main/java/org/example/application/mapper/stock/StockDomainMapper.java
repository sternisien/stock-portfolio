package org.example.application.mapper.stock;

import java.util.List;
import org.example.application.dto.StockPortfolioDto;
import org.example.domain.Stock;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StockDomainMapper {

  StockPortfolioDto domainToStockPortolioDto(Stock stock);

  List<StockPortfolioDto> domainListToStockPortfolioDto(List<Stock> stocks);
}
