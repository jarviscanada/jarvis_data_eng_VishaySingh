package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.domain.Trader;
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
    savedTrader = new Trader();
    savedTrader.setCountry("Canada");
    savedTrader.setDob("2022-07-07T20:39:29.076Z");
    savedTrader.setEmail("123@monke.com");
    savedTrader.setFirstName("monk");
    savedTrader.setLastName("e");
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
  }

  @Test
  public void delete() {
  }

  @Test
  public void deleteAll() {
  }
}