package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.util.DomainBuilder;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;

  private Account savedAccount;
  private Trader savedTrader;

  @Before
  public void setUp() throws Exception {
    savedAccount = DomainBuilder.buildAccount();
    savedTrader = DomainBuilder.buildTrader();
    traderDao.save(savedTrader);
    savedAccount.setTraderId(savedTrader.getId());
    accountDao.save(savedAccount);
  }

  @After
  public void tearDown() throws Exception {
    accountDao.deleteAll();
    traderDao.deleteAll();
  }

  @Test
  public void updateOne() {
    Double val = accountDao.findById(savedAccount.getId()).get().getAmount();
    assertEquals(100.00, val, 0);
    savedAccount.setAmount(1000.00);
    accountDao.updateOne(savedAccount);
    val = accountDao.findById(savedAccount.getId()).get().getAmount();
    assertEquals(1000.00, val, 0);
  }

  @Test
  public void delete() {
    assertEquals(1, accountDao.count());
    accountDao.delete(savedAccount);
    assertEquals(0, accountDao.count());
  }

  @Test
  public void deleteAll() {
    List<Account> accounts = Lists
        .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId(), -1)));
    assertEquals(1, accountDao.count());
    accountDao.deleteAll();
    assertEquals(0, accountDao.count());
  }
}