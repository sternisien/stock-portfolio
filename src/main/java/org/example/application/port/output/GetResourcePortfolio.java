package org.example.application.port.output;

import org.example.domain.Portfolio;

public interface GetResourcePortfolio {

  Portfolio getUserPortfolio(Long userId);
}
