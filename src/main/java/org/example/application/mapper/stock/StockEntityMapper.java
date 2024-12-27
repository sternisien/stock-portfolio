package org.example.application.mapper.stock;

import org.example.adapter.out.entity.StockEntity;
import org.example.domain.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockEntityMapper {

  Stock map(StockEntity stockEntity);
}
