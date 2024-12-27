package org.example.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Stock {

  private Long id;

  private String symbol;

  private int quantite;

  private double price;

  private LocalDateTime lastUpdated;
  private Long userId;

  public Stock() {}

  public Stock(
      Long id, String symbol, int quantite, double price, LocalDateTime lastUpdated, Long userId) {
    this.id = id;
    this.symbol = symbol;
    this.quantite = quantite;
    this.price = price;
    this.lastUpdated = lastUpdated;
    this.userId = userId;
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

  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantite) {
    this.quantite = quantite;
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
