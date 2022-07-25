package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.PortfolioView;
import ca.jrvs.apps.trading.domain.Position;
import ca.jrvs.apps.trading.domain.SecurityRow;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.domain.TraderAccountView;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DashboardService {

  private final TraderDao traderDao;
  private final AccountDao accountDao;
  private final PositionDao positionDao;
  private final QuoteDao quoteDao;

  @Autowired
  public DashboardService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao,
      QuoteDao quoteDao) {
    this.traderDao = traderDao;
    this.accountDao = accountDao;
    this.positionDao = positionDao;
    this.quoteDao = quoteDao;
  }

  /**
   * Create/return a traderAccountView by trader ID
   *
   * @param traderId
   * @return
   */
  public TraderAccountView getTraderAccount(Integer traderId) {
    Optional<Account> traderAccount = accountDao.findById(traderId);
    if (!traderAccount.isPresent()) {
      throw new IllegalArgumentException("Account not found");
    }

    Optional<Trader> trader = traderDao.findById(traderId);
    if (!trader.isPresent()) {
      throw new IllegalArgumentException("Trader not found");
    }

    return new TraderAccountView(traderAccount.get(), trader.get());
  }

  /**
   * Create/return portfolioView by traderId
   *
   * @param traderId
   * @return
   */
  public PortfolioView getProfileViewByTraderId(Integer traderId) {
    Optional<Account> traderAccount = accountDao.findById(traderId);
    if (!traderAccount.isPresent()) {
      throw new IllegalArgumentException("Account not found");
    }

    List<Position> positionList = positionDao.findAll();
    positionList = positionList.stream().filter((x) -> Objects.equals(x.getAccountId(), traderId))
        .collect(Collectors.toList());

    //Create the security rows, position.ticker = quote.ticker
    List<SecurityRow> rows = positionList.stream()
        .map((x) -> new SecurityRow(x, quoteDao.findById(x.getTicker()).get(), x.getTicker()))
        .collect(Collectors.toList());

    return new PortfolioView(rows);
  }

  /**
   * Returns all traders from db
   *
   * @return
   */
  public List<Trader> getTraders() {
    return traderDao.findAll();
  }

  private Account findAccountByTraderId(Integer traderId) {
    return accountDao.findById(traderId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid traderId"));
  }
}
