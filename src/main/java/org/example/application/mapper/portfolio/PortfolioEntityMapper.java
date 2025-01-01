package org.example.application.mapper.portfolio;

import org.example.adapter.output.entity.PortfolioEntity;
import org.example.domain.Portfolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioEntityMapper {

  Portfolio toDomain(PortfolioEntity portfolioEntity);
}
