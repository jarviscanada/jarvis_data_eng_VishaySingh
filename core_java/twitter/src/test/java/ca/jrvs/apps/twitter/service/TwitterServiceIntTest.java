package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  private CrdDao dao;
  private Service service;
  private static long time = System.currentTimeMillis();
  private static String hashtag = "#testing #twitterapi";
  private static String text =
      "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms";
  private static Double lat = 1d;
  private static Double lon = -1d;
  private static String id;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    dao = new TwitterDAO(httpHelper);
    this.service = new TwitterService(dao);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void postTweet() {
    //test valid tweet
    Tweet post = buildTweet();
    Tweet responseTweet = service.postTweet(post);
    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    System.out.println(responseTweet.getCoordinates().getCoordinates().toString());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));

    id = responseTweet.getIdStr();

    //test invalid tweet
    Tweet type0 = buildInvalidTweet(0);
    resetTweetParameters();
    Tweet type1 = buildInvalidTweet(1);
    resetTweetParameters();
    Tweet type2 = buildInvalidTweet(2);
    try {
      service.postTweet(type0);
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      service.postTweet(type1);
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
    try {
      service.postTweet(type2);
    } catch (IllegalArgumentException e){
      assertTrue(true);
    }
  }

  @Test
  public void showTweet() {
  }

  @Test
  public void deleteTweets() {
  }

  private Tweet buildTweet() {
    Coordinates coordinates = new Coordinates();
    List<Double> doubleList = new ArrayList<>();
    doubleList.add(lon);
    doubleList.add(lat);
    coordinates.setCoordinates(doubleList);
    Tweet post = new Tweet();
    post.setText(text);
    post.setCoordinates(coordinates);
    return post;
  }

  private Tweet buildInvalidTweet(int type) {
    time = System.currentTimeMillis();
    hashtag = "#testing #twitterapi";
    if (type == 0){
      text = "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms"
      + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
      + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
      + "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"
      + "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
      + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    } else if (type == 1) {
      lat = 91d;
    } else {
      lon = -181d;
    }
    return buildTweet();
  }

  private void resetTweetParameters() {
    time = System.currentTimeMillis();
    hashtag = "#testing #twitterapi";
    text = "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms";
    lat = 1d;
    lon = -1d;
  }
}