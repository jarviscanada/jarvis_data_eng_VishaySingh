package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Quote;
import ca.jrvs.apps.trading.domain.SecurityOrder;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.util.DomainBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class SecurityOrderDaoTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;

  private Account savedAccount;
  private Quote savedQuote;
  private SecurityOrder savedSecurityOrder;
  private Trader savedTrader;

  @Before
  public void setUp() throws Exception {
    savedQuote = DomainBuilder.buildQuote();
    savedAccount = DomainBuilder.buildAccount();
    savedTrader = DomainBuilder.buildTrader();
    savedAccount.setTraderId(savedTrader.getId());
    savedSecurityOrder.setAccountId(savedAccount.getId());
    savedSecurityOrder.setTicker(savedQuote.getTicker());
    quoteDao.save(savedQuote);
    traderDao.save(savedTrader);
    accountDao.save(savedAccount);
    securityOrderDao.save(savedSecurityOrder);
  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }

  @Test
  public void updateOne() {
  }

  @Test
  public void delete() {
  }

  @Test
  public void deleteAll() {
  }
}