package org.example.adapter.output.repository;

import java.util.List;
import java.util.Optional;
import org.example.adapter.output.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

  List<StockEntity> findByPortfolioUserIdOrderBySymbolAsc(Long userId);

  Optional<StockEntity> findByPortfolioUserIdAndSymbolIgnoreCase(Long userId, String symbol);
}
