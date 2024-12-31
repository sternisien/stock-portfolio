package org.example.adapter.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double availableFunds;

  private LocalDateTime lastUpdated;

  private Long userId;

  @OneToMany
  @JoinColumn(name = "portfolio_id")
  private List<StockEntity> stockEntities = new ArrayList<>();

  public Portfolio() {}

  public Portfolio(Long id, double availableFunds, LocalDateTime lastUpdated, Long userId) {
    this.id = id;
    this.availableFunds = availableFunds;
    this.lastUpdated = lastUpdated;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getAvailableFunds() {
    return availableFunds;
  }

  public void setAvailableFunds(double availableFunds) {
    this.availableFunds = availableFunds;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<StockEntity> getStockEntities() {
    return stockEntities;
  }

  public void setStockEntities(List<StockEntity> stockEntities) {
    this.stockEntities = stockEntities;
  }
}
