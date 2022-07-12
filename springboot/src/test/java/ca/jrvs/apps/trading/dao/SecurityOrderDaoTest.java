package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Account;
import ca.jrvs.apps.trading.domain.Quote;
import ca.jrvs.apps.trading.domain.SecurityOrder;
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
    Double val = securityOrderDao.findById(savedSecurityOrder.getId()).get().getPrice();
    assertEquals(123.00, val, 0);
    savedSecurityOrder.setPrice(345.00);
    securityOrderDao.updateOne(savedSecurityOrder);
    val = securityOrderDao.findById(savedSecurityOrder.getId()).get().getPrice();
    assertEquals(345.00, val, 0);
  }

  @Test
  public void delete() {
    assertEquals(1, securityOrderDao.count());
    securityOrderDao.delete(savedSecurityOrder);
    assertEquals(0, securityOrderDao.count());
  }

  @Test
  public void deleteAll() {
    List<SecurityOrder> securityOrders = Lists
        .newArrayList(securityOrderDao.findAllById(Arrays.asList(savedSecurityOrder.getId(), -1)));
    assertEquals(1, securityOrderDao.count());
    securityOrderDao.deleteAll();
    assertEquals(0, securityOrderDao.count());
  }
}