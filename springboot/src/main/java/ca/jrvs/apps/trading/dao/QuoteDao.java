package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = simpleJdbcInsert;
  }

  /**
   * Saves a given entity. Use the returned instance for further operations as the save operation
   * might have changed the entity instance completely.
   *
   * @param quote must not be {@literal null}.
   * @return the saved entity will never be {@literal null}.
   */
  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getId())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * Helper that updates one quote
   *
   * @param quote
   * @return
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * Helper that makes SQL update values in objects
   *
   * @param quote
   * @return
   */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
        quote.getAskPrice(), quote.getAskSize(), quote.getId()};
  }

  /**
   * Helper that saves one quote
   *
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * Saves all given quotes.
   *
   * @param quotes must not be {@literal null}.
   * @return the saved quotes will never be {@literal null}.
   * @throws IllegalArgumentException in case the given quote is {@literal null}.
   */
  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    List<S> lst = new ArrayList<>();
    for (S quote : quotes) {
      lst.add((S) save(quote));
    }
    return lst;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param s must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<Quote> findById(String s) {
    return Optional.empty();
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param s must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public boolean existsById(String s) {
    return false;
  }

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @Override
  public Iterable<Quote> findAll() {
    return null;
  }

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param strings
   * @return
   */
  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    return 0;
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param s must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(String s) {

  }

  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Quote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {

  }
}
