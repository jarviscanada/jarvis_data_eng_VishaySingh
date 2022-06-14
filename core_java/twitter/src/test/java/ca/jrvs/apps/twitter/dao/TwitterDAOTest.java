package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterDAOTest {

  private CrdDao dao;
  private static String id;
  private static final long time = System.currentTimeMillis();

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    this.dao = new TwitterDAO(httpHelper);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void create() {
    String hashtag = "#testing #twitterapi";
    String text =
        "@Iostinsanity get better soon " + hashtag + " created at: " + time;
    Double lat = 1d;
    Double lon = -1d;
    Coordinates coordinates = new Coordinates();
    List<Double> doubleList = new ArrayList<>();
    doubleList.add(lat);
    doubleList.add(lon);
    coordinates.setCoordinates(doubleList);
    Tweet post = new Tweet();
    post.setText(text);
    post.setCoordinates(coordinates);

    Tweet responseTweet = (Tweet) dao.create(post);

    System.out.println(responseTweet.getCoordinates().getCoordinates().toString());

    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));

    id = responseTweet.getIdStr();
  }

  @Test
  public void findById() {
    Tweet responseTweet = (Tweet) dao.findById(id);
    String hashtag = "#testing #twitterapi";
    String text =
        "@Iostinsanity get better soon " + hashtag + " created at: " + time;
    Double lat = 1d;
    Double lon = -1d;

    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void deleteById() {
    Tweet responseTweet = (Tweet) dao.deleteById(id);
    String hashtag = "#testing #twitterapi";
    String text =
        "@Iostinsanity get better soon " + hashtag + " created at: " + time;
    Double lat = 1d;
    Double lon = -1d;

    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));
  }
}