package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Quote;
import java.util.ArrayList;
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
public class QuoteDaoTest {

  @Autowired
  private QuoteDao quoteDao;

  private Quote savedQuote;

  @Before
  public void setUp() throws Exception {
    savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setTicker("aapl");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void tearDown() throws Exception {
    quoteDao.deleteAll();
  }

  @Test
  public void save() {
    assertEquals(1, quoteDao.count());
  }

  @Test
  public void saveAll() {
    List<Quote> quoteList = new ArrayList<>();

    Quote quote = new Quote();
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setId("amd");
    quote.setLastPrice(10.1d);
    quoteList.add(quote);

    Quote quote2 = new Quote();
    quote2.setAskPrice(10d);
    quote2.setAskSize(10);
    quote2.setBidPrice(10.2d);
    quote2.setBidSize(10);
    quote2.setId("msft");
    quote2.setLastPrice(10.1d);
    quoteList.add(quote2);

    quoteDao.saveAll(quoteList);
    assertEquals(3, quoteDao.count());
  }

  @Test
  public void findById() {
    Quote quote = quoteDao.findById(savedQuote.getId()).get();
    assertEquals(savedQuote.getId(), quote.getId());
    assertEquals(savedQuote.getAskPrice(), quote.getAskPrice());
    assertEquals(savedQuote.getAskSize(), quote.getAskSize());
    assertEquals(savedQuote.getBidPrice(), quote.getBidPrice());
    assertEquals(savedQuote.getLastPrice(), quote.getLastPrice());
  }

  @Test
  public void existsById() {
    assertTrue(quoteDao.existsById(savedQuote.getId()));
  }

  @Test
  public void findAll() {
    assertEquals(1, quoteDao.findAll().size());
  }

  @Test
  public void count() {
    assertEquals(1, quoteDao.count());
    quoteDao.deleteAll();
    assertEquals(0, quoteDao.count());
  }

  @Test
  public void deleteById() {
    quoteDao.deleteById(savedQuote.getId());
    assertEquals(0, quoteDao.count());
  }

  @Test
  public void deleteAll() {
    quoteDao.deleteAll();
    assertEquals(0, quoteDao.count());
  }
}