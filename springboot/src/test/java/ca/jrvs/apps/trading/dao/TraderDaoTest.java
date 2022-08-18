package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Trader;
import ca.jrvs.apps.trading.util.DomainBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class TraderDaoTest {

  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;

  @Before
  public void setUp() throws Exception {
    savedTrader = DomainBuilder.buildTrader();
    traderDao.save(savedTrader);
  }

  @After
  public void tearDown() throws Exception {
    traderDao.deleteAll();
  }

  @Test
  public void findAllById() {
    List<Trader> traders = Lists
        .newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId(), -1)));
    assertEquals(1, traders.size());
    assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
  }

  @Test
  public void updateOne() {
    try {
      traderDao.updateOne(savedTrader);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void delete() {
    try {
      traderDao.delete(savedTrader);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteAll() {
    List<Trader> traders = new ArrayList<>();
    traders.add(savedTrader);
    try {
      traderDao.deleteAll(traders);
      fail();
    } catch (UnsupportedOperationException e) {
      assertTrue(true);
    }
  }
}