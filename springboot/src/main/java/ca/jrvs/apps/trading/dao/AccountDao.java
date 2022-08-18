package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.Account;
import java.util.stream.StreamSupport;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

  private final String TABLE_NAME = "account";
  private final String ID_COLUMN = "id";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleInsert;

  @Autowired
  public AccountDao(DataSource dataSource) {
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
  Class<Account> getEntityClass() {
    return Account.class;
  }

  /**
   * Helper that updates one entity
   *
   * @param entity
   * @return
   */
  @Override
  public int updateOne(Account entity) {
    String update_sql = "UPDATE " + getTableName() + " SET amount=?, trader_id=? WHERE " + ID_COLUMN
        + "=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(entity));
  }

  /**
   * Helper that makes SQL update values in objects
   *
   * @param entity
   * @return
   */
  private Object[] makeUpdateValues(Account entity) {
    return new Object[]{entity.getAmount(), entity.getId(), entity.getTraderId()};
  }

  /**
   * Deletes a given entity.
   *
   * @param entity
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public void delete(Account entity) {
    if (!this.existsById(entity.getId())) {
      throw new IllegalArgumentException("ID not found");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    jdbcTemplate.update(deleteSql, entity.getId());
  }

  /**
   * Deletes the given entities.
   *
   * @param entities
   * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
   */
  @Override
  public void deleteAll(Iterable<? extends Account> entities) {
    StreamSupport.stream(entities.spliterator(), false).forEach(this::delete);
  }
}
