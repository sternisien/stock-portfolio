package org.example.adapter.output.repository;

import java.util.Optional;
import org.example.adapter.output.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

  Optional<PortfolioEntity> findByUserId(Long userId);
}
