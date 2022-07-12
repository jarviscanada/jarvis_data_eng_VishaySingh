package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.SecurityOrder;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.domain.TraderAccountView;
import ca.jrvs.apps.trading.util.DomainBuilder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

  private final TraderDao traderDao;
  private final AccountDao accountDao;
  private final PositionDao positionDao;
  private final SecurityOrderDao securityOrderDao;

  @Autowired
  public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao,
      SecurityOrderDao securityOrderDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.securityOrderDao = securityOrderDao;
  }

  /**
   * Create a new trader and init an account with 0 amount
   *
   * @param trader
   * @return
   */
  public TraderAccountView createTraderAndAccount(Trader trader) {
    validateTrader(trader);
    trader = traderDao.save(trader);
    Account account = DomainBuilder.buildAccount();
    account.setAmount(0.0);
    account.setTraderId(trader.getId());
    account = accountDao.save(account);
    return new TraderAccountView(account, trader);
  }

  /**
   * Trader can be deleted iff it has no open position and 0 cash balance
   *
   * @param traderId
   */
  public void deleteTraderById(Integer traderId) {
    validateTraderId(traderId);

    Optional<Account> traderAccount = accountDao.findById(traderId);
    if (!traderAccount.isPresent()) {
      throw new IllegalArgumentException("Id not found");
    }

    Double balance = traderAccount.get().getAmount();
    if (balance > 0) {
      throw new IllegalArgumentException("Balance is not 0");
    }

    //delete all securities
    List<SecurityOrder> securityOrders = securityOrderDao.findAll();
    securityOrders.stream()
        .filter((x) -> Objects.equals(x.getAccountId(), traderId))
        .forEach((x) -> securityOrderDao.delete(x));

    accountDao.delete(traderAccount.get()); //delete account
    traderDao.deleteById(traderId); //delete trader
  }

  /**
   * Deposit fund to account with traderId
   *
   * @param traderId
   * @param fund
   * @return
   */
  public Account deposit(Integer traderId, Double fund) {
    validateTraderId(traderId);
    validateFunds(fund);

    Optional<Account> traderAccount = accountDao.findById(traderId);
    if (!traderAccount.isPresent()) {
      throw new IllegalArgumentException("Id not found");
    }
    traderAccount.get().setAmount(traderAccount.get().getAmount() + fund);
    accountDao.updateOne(traderAccount.get());
    return accountDao.findById(traderId).get();
  }

  /**
   * Withdraw a fund of an account by TraderId
   *
   * @param traderId
   * @param fund
   * @return
   */
  public Account withdraw(Integer traderId, Double fund) {
    validateTraderId(traderId);
    validateFunds(fund);

    Optional<Account> traderAccount = accountDao.findById(traderId);
    if (!traderAccount.isPresent()) {
      throw new IllegalArgumentException("Id not found");
    }
    if (traderAccount.get().getAmount() - fund < 0) {
      throw new IllegalArgumentException("Insufficient funds");
    }

    traderAccount.get().setAmount(traderAccount.get().getAmount() - fund);
    accountDao.updateOne(traderAccount.get());
    return accountDao.findById(traderId).get();
  }

  private void validateTrader(Trader trader) {
    if (trader.getCountry() == null || trader.getDob() == null || trader.getEmail() == null |
        trader.getFirstName() == null || trader.getLastName() == null || trader.getId() != null) {
      throw new IllegalArgumentException("Trader has null values, or ID is not null");
    }
  }

  private void validateTraderId(Integer traderId) {
    if (traderId == null || traderId < 0) {
      throw new IllegalArgumentException("Id must not be null or invalid");
    }
  }

  private void validateFunds(Double funds) {
    if (funds <= 0) {
      throw new IllegalArgumentException("Funds must be positive");
    }
  }
}
