package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private final HttpClientConnectionManager httpClientConnectionManager;

  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Optional<IexQuote> findById(String s) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = (List<IexQuote>) findAllById(Collections.singletonList(s));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  private Optional<String> executeHttpGet(String url) {
    return null;
  }

  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        .setConnectionManagerShared(true)
        .build();
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    return null;
  }

  @Override
  public Iterable<IexQuote> findAllById(Iterable<String> iterable) {
    List<IexQuote> quotes = new ArrayList<>();

    String uri = IEX_BATCH_URL;
    StringBuilder symbolBuilder = new StringBuilder();
    int start = IEX_BATCH_URL.length() - 1;
    for (String ticker : iterable) {
      symbolBuilder.append(ticker);
      symbolBuilder.append(",%s");
      start = start + ticker.length() + 2;
    }
    symbolBuilder.delete(start, start + 3);
    uri = String.format(uri, symbolBuilder);

    String response = executeHttpGet(uri)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    JSONObject IexQuotesJson = new JSONObject(response);

    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    //return new JSONArray(IexQuotesJson);
    return quotes;
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
