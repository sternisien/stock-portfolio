package org.example.domain;

import java.time.LocalDateTime;

public class Stock {

  private Long id;

  private String symbol;

  private int quantity;

  private double price;

  private LocalDateTime lastUpdated;

  private Long portfolioId;

  public Stock() {}

  public Stock(
      Long id,
      String symbol,
      int quantity,
      double price,
      LocalDateTime lastUpdated,
      Long portfolioId) {
    this.id = id;
    this.symbol = symbol;
    this.quantity = quantity;
    this.price = price;
    this.lastUpdated = lastUpdated;
    this.portfolioId = portfolioId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getsymbol() {
    return symbol;
  }

  public void setsymbol(String symbol) {
    this.symbol = symbol;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Long getPortfolioId() {
    return portfolioId;
  }

  public void setPortfolioId(Long portfolioId) {
    this.portfolioId = portfolioId;
  }
}
