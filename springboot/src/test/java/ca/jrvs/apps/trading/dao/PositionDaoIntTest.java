package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Position;
import ca.jrvs.apps.trading.domain.Quote;
import ca.jrvs.apps.trading.domain.SecurityOrder;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.util.DomainBuilder;
import java.util.Arrays;
import java.util.List;
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
public class PositionDaoIntTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;
  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private PositionDao positionDao;

  private Account savedAccount;
  private Quote savedQuote;
  private SecurityOrder savedSecurityOrder;
  private Trader savedTrader;
  private Position savedPosition;

  @Before
  public void setUp() throws Exception {
    savedQuote = DomainBuilder.buildQuote();
    quoteDao.save(savedQuote);

    savedAccount = DomainBuilder.buildAccount();
    savedTrader = DomainBuilder.buildTrader();
    traderDao.save(savedTrader);

    savedAccount.setTraderId(savedTrader.getId());
    accountDao.save(savedAccount);

    savedSecurityOrder = DomainBuilder.buildSecurityOrder();
    savedSecurityOrder.setAccountId(savedAccount.getId());
    savedSecurityOrder.setTicker(savedQuote.getTicker());
    securityOrderDao.save(savedSecurityOrder);

    savedPosition = positionDao.findById(savedSecurityOrder.getId(), savedSecurityOrder.getTicker())
        .get();
  }

  @After
  public void tearDown() throws Exception {
    securityOrderDao.deleteAll();
    accountDao.deleteAll();
    traderDao.deleteAll();
    quoteDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Position> positionList = positionDao
        .findAllById(Arrays.asList(savedPosition.getAccountId(), -1),
            Arrays.asList(savedPosition.getTicker(), "amd"));
    assertEquals(1, positionList.size());
    assertEquals(savedPosition.getPosition(), positionList.get(0).getPosition());
  }

  @Test
  public void existsById() {
    assertTrue(positionDao.existsById(savedPosition.getId(), savedPosition.getTicker()));
  }

  @Test
  public void updateOne() {
    try {
      positionDao.updateOne(null);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void save() {
    try {
      positionDao.save(null);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void saveAll() {
    try {
      positionDao.saveAll(null);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void delete() {
    try {
      positionDao.delete(null);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteAll() {
    try {
      positionDao.deleteAll(null);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
}