package org.example.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {

  private Long id;
  private double availableFunds;

  private double fundsLocked;

  private LocalDateTime lastUpdated;
  private Long userId;

  private List<Stock> stocks = new ArrayList<>();

  public Portfolio() {}

  public Portfolio(
      Long id,
      double availableFunds,
      double fundsLocked,
      LocalDateTime lastUpdated,
      Long userId,
      List<Stock> stocks) {
    this.id = id;
    this.availableFunds = availableFunds;
    this.fundsLocked = fundsLocked;
    this.lastUpdated = lastUpdated;
    this.userId = userId;
    this.stocks = stocks;
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

  public List<Stock> getStocks() {
    return stocks;
  }

  public void setStocks(List<Stock> stocks) {
    this.stocks = stocks;
  }

  public double getFundsLocked() {
    return fundsLocked;
  }

  public void setFundsLocked(double fundsLocked) {
    this.fundsLocked = fundsLocked;
  }
}
