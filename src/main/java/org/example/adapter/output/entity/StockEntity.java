package org.example.adapter.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
public class StockEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String symbol;

  private int quantity;

  private int quantityLock;

  private double price;

  private LocalDateTime lastUpdated;

  @ManyToOne
  @JoinColumn(name = "portfolio_id")
  private Portfolio portfolio;

  public StockEntity() {}

  public StockEntity(
      Long id,
      String symbol,
      int quantity,
      int quantityLock,
      double price,
      LocalDateTime lastUpdated,
      Portfolio portfolio) {
    this.id = id;
    this.symbol = symbol;
    this.quantity = quantity;
    this.quantityLock = quantityLock;
    this.price = price;
    this.lastUpdated = lastUpdated;
    this.portfolio = portfolio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantityLock() {
    return quantityLock;
  }

  public void setQuantityLock(int quantityLock) {
    this.quantityLock = quantityLock;
  }

  public void setQuantite(int quantity) {
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

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }
}
