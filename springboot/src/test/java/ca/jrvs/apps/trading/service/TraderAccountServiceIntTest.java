package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.domain.TraderAccountView;
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
public class TraderAccountServiceIntTest {

  private TraderAccountView savedView;
  private Trader savedTrader;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Before
  public void setUp() throws Exception {
    savedTrader = DomainBuilder.buildTrader();
  }

  @After
  public void tearDown() throws Exception {
    traderDao.deleteAll();
    accountDao.deleteAll();
  }

  @Test
  public void createTraderAndAccount() {
    savedView = traderAccountService.createTraderAndAccount(savedTrader);
    Trader newTrader = savedView.getTrader();
    Account newAccount = savedView.getAccount();
    assertEquals(savedTrader.getId(), newTrader.getId());
    assertEquals(savedTrader.getEmail(), newTrader.getEmail());
    assertEquals(savedTrader.getId(), newAccount.getId());
    assertEquals(savedTrader.getId(), newAccount.getTraderId());
    assertEquals(0.0, newAccount.getAmount(), 0);
  }

  @Test
  public void deleteTraderById() {
    savedView = traderAccountService.createTraderAndAccount(savedTrader);
    assertEquals(1, traderDao.count());
    assertEquals(1, accountDao.count());
    traderAccountService.deleteTraderById(savedTrader.getId());
    assertEquals(0, traderDao.count());
    assertEquals(0, accountDao.count());
  }

  @Test
  public void deposit() {
    savedView = traderAccountService.createTraderAndAccount(savedTrader);
    assertEquals(0.0, savedView.getAccount().getAmount(), 0);
    Account deposited = traderAccountService.deposit(savedTrader.getId(), 125.00);
    assertEquals(125.00, deposited.getAmount(), 0);

    try {
      traderAccountService.deposit(savedTrader.getId(), -125.00);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.deposit(217398712, 125.00);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void withdraw() {
    savedView = traderAccountService.createTraderAndAccount(savedTrader);
    assertEquals(0.0, savedView.getAccount().getAmount(), 0);
    Account deposited = traderAccountService.deposit(savedTrader.getId(), 125.00);
    assertEquals(125.00, deposited.getAmount(), 0);

    deposited = traderAccountService.withdraw(savedTrader.getId(), 100.00);
    assertEquals(25.00, deposited.getAmount(), 0);

    try {
      traderAccountService.withdraw(savedTrader.getId(), -125.00);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      traderAccountService.withdraw(217398712, 125.00);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}