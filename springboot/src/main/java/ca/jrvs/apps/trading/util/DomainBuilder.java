package ca.jrvs.apps.trading.util;

import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Trader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DomainBuilder {

  public static Trader buildTrader() {
    Trader savedTrader = new Trader();
    savedTrader.setCountry("Canada");
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    df = df.withLocale(Locale.ROOT);
    savedTrader.setDob(LocalDate.parse("01-01-2022", df));
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
}
