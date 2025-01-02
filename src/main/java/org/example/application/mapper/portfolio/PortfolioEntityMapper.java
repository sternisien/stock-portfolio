package org.example.application.mapper.portfolio;

import org.example.adapter.output.entity.PortfolioEntity;
import org.example.domain.Portfolio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PortfolioEntityMapper {

  @Mapping(target = "stocks", ignore = true)
  Portfolio toDomain(PortfolioEntity portfolioEntity);
}
