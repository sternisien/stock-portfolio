package org.example.adapter.in;

import org.example.application.dto.BalancePortfolioDto;
import org.example.application.port.in.GetDataAssetPortfolioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

  private final GetDataAssetPortfolioUseCase assetPortfolioUseCase;

  public PortfolioController(GetDataAssetPortfolioUseCase assetPortfolioUseCase) {
    this.assetPortfolioUseCase = assetPortfolioUseCase;
  }

  @GetMapping("/{userId}/balance")
  public ResponseEntity<BalancePortfolioDto> getBalanceUserPortfolio(@PathVariable Long userId) {
    return ResponseEntity.ok(assetPortfolioUseCase.getBalanceUserPortfolio(userId));
  }
}
