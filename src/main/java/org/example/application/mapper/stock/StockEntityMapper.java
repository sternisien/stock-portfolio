package org.example.application.mapper.stock;

import org.example.adapter.output.entity.StockEntity;
import org.example.domain.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockEntityMapper {

  // TODO: Revoir si nécessaire mapping Bi-directionnelle utile ? sinon mapping à revoir
  @Mapping(source = "portfolio.id", target = "portfolioId")
  Stock map(StockEntity stockEntity);
}
