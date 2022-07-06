package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.domain.IexQuote;
import ca.jrvs.apps.trading.domain.Quote;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private final QuoteDao quoteDao;
  private final MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(MarketDataDao marketDataDao, QuoteDao quoteDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Find an IexQuote
   *
   * @param ticker
   * @return
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Update quote table against IEX source
   */
  public void updateMarketData() {
    //get all quotes from db
    List<Quote> quoteList = findAllQuotes();

    //for each ticker:
    quoteList.stream()
        .map((x) -> findIexQuoteByTicker(x.getTicker())) //get iexQuote
        .map(QuoteService::buildQuoteFromIexQuote) //convert iexQuote to quote
        .forEach(this::saveQuote); //persist quote to db
  }

  /**
   * Helper to convert IexQuote to Quote
   *
   * @param iexQuote
   * @return
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setId(iexQuote.getSymbol().toLowerCase(Locale.ROOT));
    quote.setTicker(iexQuote.getSymbol().toLowerCase(Locale.ROOT));
    if (iexQuote.getLatestPrice() == null) {
      quote.setLastPrice(iexQuote.getLatestPrice());
    } else {
      quote.setLastPrice(iexQuote.getIexClose());
    }
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setAskSize(iexQuote.getIexAskSize());
    return quote;
  }

  /**
   * Validate against IEX and save given tickers to quote table
   *
   * @param tickers
   * @return
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    //for each ticker:
    return tickers.stream()
        .map(this::saveQuote) //convert ticker, persist to db
        .collect(Collectors.toList()); //convert to list
  }

  /**
   * Helper method
   *
   * @param ticker
   * @return
   */
  private Quote saveQuote(String ticker) {
    return this.saveQuote(QuoteService.buildQuoteFromIexQuote(this.findIexQuoteByTicker(ticker)));
  }

  /**
   * Update a given quote to quote table without validation
   *
   * @param quote
   * @return
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   *
   * @return
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}
