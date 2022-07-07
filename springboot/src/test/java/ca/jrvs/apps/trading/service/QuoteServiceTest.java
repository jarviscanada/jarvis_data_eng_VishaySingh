package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.domain.IexQuote;
import ca.jrvs.apps.trading.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
public class QuoteServiceTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setUp() throws Exception {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("aapl");
    assertEquals("aapl", iexQuote.getSymbol().toLowerCase(Locale.ROOT));
  }

  @Test
  public void updateMarketData() {
    assertEquals(0, quoteDao.count());
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setTicker("aapl");
    savedQuote.setLastPrice(10.1d);
    assertEquals(0, quoteDao.count());
    quoteService.saveQuote(savedQuote);
    quoteService.updateMarketData();
    assertEquals("aapl",
        quoteService.findIexQuoteByTicker("aapl").getSymbol().toLowerCase(Locale.ROOT));
    assertNotEquals(savedQuote.getAskPrice(),
        quoteService.findIexQuoteByTicker("aapl").getIexAskPrice());
  }

  @Test
  public void saveQuotes() {
    List<String> tickers = new ArrayList<>();
    tickers.add("aapl");
    tickers.add("amd");
    assertEquals(0, quoteDao.count());
    quoteService.saveQuotes(tickers);
    assertEquals(2, quoteDao.count());
  }

  @Test
  public void saveQuote() {
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setTicker("aapl");
    savedQuote.setLastPrice(10.1d);
    assertEquals(0, quoteDao.count());
    quoteService.saveQuote(savedQuote);
    assertEquals(1, quoteDao.count());
  }

  @Test
  public void saveQuoteByTicker() {
    assertEquals(0, quoteDao.count());
    quoteService.saveQuote("aapl");
    assertEquals(1, quoteDao.count());
  }

  @Test
  public void findAllQuotes() {
    assertEquals(0, quoteDao.count());
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setTicker("aapl");
    savedQuote.setLastPrice(10.1d);
    quoteService.saveQuote(savedQuote);
    assertNotEquals(0, quoteService.findAllQuotes().size());
  }
}