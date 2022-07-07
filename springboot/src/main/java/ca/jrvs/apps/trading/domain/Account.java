package ca.jrvs.apps.trading.domain;

public class Account implements Entity<Integer> {

  /*
  amount	number($double)
  id	integer($int32)
  traderId	integer($int32)
   */
  private Integer id;


  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    id = integer;
  }
}
