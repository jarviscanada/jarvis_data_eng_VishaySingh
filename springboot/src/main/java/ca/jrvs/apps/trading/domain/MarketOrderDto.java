package ca.jrvs.apps.trading.domain;

public class MarketOrderDto implements Entity<Integer>{

  /*
  accountId	integer($int32)
  size	integer($int32)
  ticker	string
   */
  private Integer accountId;
  private Integer size;
  private String ticker;

  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer integer) {
    this.accountId = integer;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }
}
