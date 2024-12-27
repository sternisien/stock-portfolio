package org.example.application.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record StockPortfolioDto(
    String symbol, int quantite, double price, LocalDateTime lastUpdated) {}
