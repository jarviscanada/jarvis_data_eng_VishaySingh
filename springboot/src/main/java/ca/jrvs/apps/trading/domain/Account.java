package ca.jrvs.apps.trading.domain;

public class Account implements Entity<Integer> {

  /*
  amount	number($double)
  id	integer($int32)
  traderId	integer($int32)
   */
  private Integer id;
  private Double amount;
  private Integer traderId;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    id = integer;
  }
}
