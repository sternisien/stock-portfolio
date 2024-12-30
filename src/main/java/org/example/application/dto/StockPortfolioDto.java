package org.example.application.dto;


import java.time.LocalDateTime;

public record StockPortfolioDto(
    String symbol, int quantity, double price, LocalDateTime lastUpdated) {}
