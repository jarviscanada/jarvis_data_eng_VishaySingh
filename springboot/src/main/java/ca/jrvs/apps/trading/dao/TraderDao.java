package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.Trader;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {

  private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);

  private final String TABLE_NAME = "trader";
  private final String ID_COLUMN = "id";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public TraderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Trader> getEntityClass() {
    return Trader.class;
  }

  /**
   * Helper that updates one entity
   *
   * @param entity
   * @return
   */
  @Override
  public int updateOne(Trader entity) {
    throw new UnsupportedOperationException("Not implemented");
  }


  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Trader entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Trader> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }
}