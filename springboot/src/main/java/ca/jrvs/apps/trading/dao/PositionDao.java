package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao extends JdbcCrudDao<Position> {

  private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

  private final String TABLE_NAME = "position";
  private final String ID_COLUMN1 = "account_id";
  private final String ID_COLUMN2 = "ticker";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN1, ID_COLUMN2);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return this.jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return this.simpleInsert;
  }

  @Override
  public String getTableName() {
    return this.TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return this.ID_COLUMN1;
  }

  public String getIdColumn2Name() {
    return this.ID_COLUMN2;
  }

  @Override
  Class<Position> getEntityClass() {
    return Position.class;
  }

  /**
   * Retrieves an entity by its id.
   *
   * @param accountId must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  public Optional<Position> findById(Integer accountId, String ticker) {
    Optional<Position> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?"
        + " AND " + getIdColumn2Name() + " =?";

    try {
      entity = Optional.ofNullable(getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), accountId, ticker));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find id:" + accountId, e);
    }
    return entity;
  }

  /**
   * Returns whether an entity with the given id exists.
   *
   * @param id must not be {@literal null}.
   * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  public boolean existsById(Integer id, String ticker) {
    Optional<Position> entity = findById(id, ticker);
    return entity.isPresent();
  }

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param ids
   * @return
   */
  public List<Position> findAllById(List<Integer> ids, List<String> tickers) {
    if (ids.size() != tickers.size()) {
      return Collections.emptyList();
    }
    List<Position> positionList = new ArrayList<>();
    for (int i = 0; i < ids.size(); i++) {
      Optional<Position> position = this.findById(ids.get(i), tickers.get(i));
      position.ifPresent(positionList::add);
    }
    return positionList;
  }

  /**
   * Helper that updates one entity
   *
   * @param entity
   * @return
   */
  @Override
  public int updateOne(Position entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends Position> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends Position> List<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Position entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Position> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
