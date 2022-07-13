package ca.jrvs.apps.trading.util;

import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Quote;
import ca.jrvs.apps.trading.domain.SecurityOrder;
import ca.jrvs.apps.trading.domain.Trader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DomainBuilder {

  public static Trader buildTrader() {
    Trader savedTrader = new Trader();
    savedTrader.setCountry("Canada");
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    df = df.withLocale(Locale.ROOT);
    savedTrader.setDob(Date.valueOf(LocalDate.parse("01-01-2022", df)));
    savedTrader.setEmail("123@monke.com");
    savedTrader.setFirstName("monk");
    savedTrader.setLastName("e");
    return savedTrader;
  }

  public static Account buildAccount() {
    Account account = new Account();
    account.setAmount(100.00);
    return account;
  }

  public static SecurityOrder buildSecurityOrder() {
    SecurityOrder securityOrder = new SecurityOrder();
    securityOrder.setNotes("notes");
    securityOrder.setPrice(123.00);
    securityOrder.setSize(3);
    securityOrder.setStatus("FILLED");
    return securityOrder;
  }

  public static Quote buildQuote() {
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setTicker("aapl");
    savedQuote.setLastPrice(10.1d);
    return savedQuote;
  }
}
