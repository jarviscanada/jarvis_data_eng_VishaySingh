package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.domain.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getIdColumnName();

  abstract Class<T> getEntityClass();

  /**
   * Save entity, update auto-generated int ID
   *
   * @param entity must not be {@literal null}.
   * @param <S>
   * @return
   */
  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      int updatedRowNo = updateOne(entity);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update entity");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * Saves all given entities.
   *
   * @param entities must not be {@literal null}.
   * @return the saved entities will never be {@literal null}.
   * @throws IllegalArgumentException in case the given entity is {@literal null}.
   */
  @Override
  public <S extends T> List<S> saveAll(Iterable<S> entities) {
    return StreamSupport.stream(entities.spliterator(), false)
        .map(this::save)
        .collect(Collectors.toList());
  }

  /**
   * Helper that saves one entity
   *
   * @param entity
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * Helper that updates one entity
   *
   * @param entity
   * @return
   */
  abstract public int updateOne(T entity);

  /**
   * Retrieves an entity by its id.
   *
   * @param id must not be {@literal null}.
   * @return the entity with the given id or {@literal Optional#empty()} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}.
   */
  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

    try {
      entity = Optional.ofNullable(getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find id:" + id, e);
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
  @Override
  public boolean existsById(Integer id) {
    Optional<T> entity = findById(id);
    return entity.isPresent();
  }

  /**
   * Returns all instances of the type.
   *
   * @return all entities
   */
  @Override
  public List<T> findAll() {
    String selectSql = "SELECT * FROM " + getTableName();
    List<T> entities = getJdbcTemplate()
        .query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
    return entities;
  }

  /**
   * Returns all instances of the type with the given IDs.
   *
   * @param ids
   * @return
   */
  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    return StreamSupport.stream(ids.spliterator(), false)
        .filter((x) -> this.findById(x).isPresent())
        .map((x) -> this.findById(x).get())
        .collect(Collectors.toList());
  }

  /**
   * Returns the number of entities available.
   *
   * @return the number of entities
   */
  @Override
  public long count() {
    return findAll().size();
  }

  /**
   * Deletes the entity with the given id.
   *
   * @param id must not be {@literal null}.
   * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
   */
  @Override
  public void deleteById(Integer id) {
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    getJdbcTemplate().update(deleteSql, id);
  }

  /**
   * Deletes all entities managed by the repository.
   */
  @Override
  public void deleteAll() {
    String deleteSql = "TRUNCATE " + getTableName() + " CASCADE";
    getJdbcTemplate().update(deleteSql);
  }

}
