package ca.jrvs.apps.trading.domain;

public class Position implements Entity<Integer> {
  /*
  accountId	integer($int32)
  position	integer($int32)
  ticker	string
   */

  private Integer accountId;
  private Integer position;
  private String ticker;

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer integer) {
    this.accountId = integer;
  }
}
