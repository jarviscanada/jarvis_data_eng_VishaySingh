package ca.jrvs.apps.trading.domain;

import org.springframework.stereotype.Component;

@Component
public class Quote implements Entity<String> {

  private String ticker; //symbol.lower
  private Double lastPrice; //latestPrice (if not null, else use iexClose)
  private Double bidPrice; //iexBidPrice
  private Integer bidSize; //iexBidSize
  private Double askPrice; //iexAskPrice
  private Integer askSize; //iexAskSize

  @Override
  public String getId() {
    return ticker;
  }

  @Override
  public void setId(String s) {
    ticker = s;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String s) {
    ticker = s;
  }

  public Double getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(Double lastPrice) {
    this.lastPrice = lastPrice;
  }

  public Double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(Double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public Integer getBidSize() {
    return bidSize;
  }

  public void setBidSize(Integer bidSize) {
    this.bidSize = bidSize;
  }

  public Double getAskPrice() {
    return askPrice;
  }

  public void setAskPrice(Double askPrice) {
    this.askPrice = askPrice;
  }

  public Integer getAskSize() {
    return askSize;
  }

  public void setAskSize(Integer askSize) {
    this.askSize = askSize;
  }
}
