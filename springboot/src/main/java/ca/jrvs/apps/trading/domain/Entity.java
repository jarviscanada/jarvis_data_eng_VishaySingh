package ca.jrvs.apps.trading.domain;

public interface Entity<ID> {

  ID getId();

  void setId(ID id);
}
