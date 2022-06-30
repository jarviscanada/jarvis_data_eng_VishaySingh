package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.util.JsonParser;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private final HttpClientConnectionManager httpClientConnectionManager;
  private final int HTTP_OK = 299;
  private static final String QUOTE = "quote";

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

  /**
   * Execute a get and return http entity/body as a string
   *
   * @param url resource url
   * @return http response body or Optional.empty for 404 response
   */
  private Optional<String> executeHttpGet(String url) {
    HttpGet request = new HttpGet(url);
    CloseableHttpClient closeableHttpClient = getHttpClient();
    CloseableHttpResponse response = null;
    try {
      response = closeableHttpClient.execute(request);
    } catch (IOException e) {
      throw new DataRetrievalFailureException("HTTP failed");
    }

    int status = response.getStatusLine().getStatusCode();
    if (status > HTTP_OK) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
        return Optional.empty();
      }
      throw new DataRetrievalFailureException("HTTP status error: " + status);
    }

    if (response.getEntity() == null) {
      Optional.empty();
    }

    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new DataRetrievalFailureException("Entity to String conversion failed", e);
    }
    return Optional.ofNullable(jsonStr);
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
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAllById(Iterable<String> iterable) {
    List<IexQuote> quotes = new ArrayList<>();

    String uri = IEX_BATCH_URL;
    StringBuilder symbolBuilder = new StringBuilder();
    for (String ticker : iterable) {
      symbolBuilder.append(ticker.toLowerCase(Locale.ROOT));
      symbolBuilder.append(',');
    }
    symbolBuilder.delete(symbolBuilder.length() - 1, symbolBuilder.length());
    uri = String.format(uri, symbolBuilder);

    String response = executeHttpGet(uri)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    JSONObject IexQuotesJson = new JSONObject(response);

    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    //Add all quotes to list
    quotes = traverseJsonObject(IexQuotesJson, quotes);
    if (Iterables.size(iterable) > quotes.size()) {
      throw new IllegalArgumentException("Invalid ticker");
    }
    return quotes;
  }

  private static List<IexQuote> traverseJsonObject(JSONObject jsonObject, List<IexQuote> quotes) {
    jsonObject.keySet().forEach(keyStr ->
    {
      Object key = jsonObject.get(keyStr);
      if (key instanceof JSONObject) {
        String jsonString = ((JSONObject) key).get(QUOTE).toString();
        try {
          IexQuote iexQuote = JsonParser.toObjectFromJson(jsonString, IexQuote.class);
          quotes.add(iexQuote);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
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
