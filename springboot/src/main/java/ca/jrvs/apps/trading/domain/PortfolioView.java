package ca.jrvs.apps.trading.domain;

import java.util.List;

public class PortfolioView {
  List<SecurityRow> securityRows;

  public List<SecurityRow> getSecurityRows() {
    return securityRows;
  }

  public void setSecurityRows(List<SecurityRow> securityRows) {
    this.securityRows = securityRows;
  }
}
