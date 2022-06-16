package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private CrdDao dao;
  private Service service;
  private Controller controller;
  private static String id;
  private static final List<String> lst = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    dao = new TwitterDAO(httpHelper);
    service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    Tweet responseTweet = this.controller.postTweet(
        new String[]{"post", "testing #twitterapi please ignore", "1.0:-1.0"});
    assertEquals("testing #twitterapi please ignore", responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(new Double(1), responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(new Double(-1), responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue("testing #twitterapi please ignore"
        .contains(responseTweet.getEntities().getHashtags().get(0).getText()));

    id = responseTweet.getIdStr();
    lst.add(id);

    // Fail case
    try {
      this.controller.postTweet(
          new String[]{"post", "", "1.0:-1.0"}
      );
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    try {
      this.controller.postTweet(
          new String[]{"post", "text", "1.0asda:ad-1.0"}
      );
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
    if (id == null) {
      Tweet responseTweet = this.controller.postTweet(
          new String[]{"post", "testing #twitterapi please ignore1", "1.0:-1.0"});
      id = responseTweet.getIdStr();
      lst.add(id);
    }
    // No fields
    Tweet responseTweet = this.controller.showTweet(new String[]{"show", id});
    assertEquals("testing #twitterapi please ignore1", responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(new Double(1), responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(new Double(-1), responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue("testing #twitterapi please ignore1"
        .contains(responseTweet.getEntities().getHashtags().get(0).getText()));

    // With fields
    responseTweet = this.controller.showTweet(
        new String[]{"show", id, "retweet_count,coordinates"});
    assertEquals("testing #twitterapi please ignore1", responseTweet.getText());
    assertTrue("testing #twitterapi please ignore"
        .contains(responseTweet.getEntities().getHashtags().get(0).getText()));
    assertNull(responseTweet.getRetweetCount()); //check fields worked
    assertNull(responseTweet.getCoordinates());

    // Fail case
    try {
      this.controller.showTweet(new String[]{"show"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      this.controller.showTweet(new String[]{"show", "", "retweet_count"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  @Test
  public void deleteTweet() {
    String text = "testing #twitterapi please ignore2";
    Tweet responseTweet = this.controller.postTweet(
        new String[]{"post", text, "1.0:-1.0"});
    id = responseTweet.getIdStr();
    lst.add(id);
    StringBuilder idBuilder = new StringBuilder();
    for (String idStr : lst) {
      idBuilder.append(idStr);
      idBuilder.append(",");
    }
    idBuilder.delete(idBuilder.length() - 1, idBuilder.length());
    List<Tweet> deletedTweets = this.controller.deleteTweet(
        new String[]{"delete", idBuilder.toString()}
    );
    assertNotNull(deletedTweets);
    assertEquals(lst.size(), deletedTweets.size());

    // Fail case
    try {
      this.controller.deleteTweet(new String[]{"delete"});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    try {
      this.controller.deleteTweet(new String[]{"delete", ""});
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}