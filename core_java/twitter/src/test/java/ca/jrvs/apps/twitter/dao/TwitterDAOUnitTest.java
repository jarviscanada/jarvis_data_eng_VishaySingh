package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDAOUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDAO dao;

  private static long time = System.currentTimeMillis();
  private static final String hashtag = "#testing #twitterapi";
  private static final String text =
      "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms";
  private static final Double lat = 1d;
  private static final Double lon = -1d;
  private static String id;
  private static final String consumerKey = System.getenv("consumerKey");
  private static final String consumerSecret = System.getenv("consumerSecret");
  private static final String accessToken = System.getenv("accessToken");
  private static final String tokenSecret = System.getenv("tokenSecret");
  private static final HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
      accessToken,
      tokenSecret);
  TwitterDAO newDao = new TwitterDAO(httpHelper);

  private Tweet buildTweet() {
    Coordinates coordinates = new Coordinates();
    List<Double> doubleList = new ArrayList<>();
    doubleList.add(lat);
    doubleList.add(lon);
    coordinates.setCoordinates(doubleList);
    Tweet post = new Tweet();
    post.setText(text);
    post.setCoordinates(coordinates);
    return post;
  }

  @Test
  public void showTweet() throws Exception {
    Tweet post = buildTweet();

    //should get exception here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(post);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //Now test a good path
    String tweetJsonStr = "{\n"
        + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + "   \"id\":1097607853932564480,\n"
        + "   \"id_str\":\"1097607853932564480\",\n"
        + "   \"text\":\"test with loc223\",\n"
        + "   \"entities\":{\n"
        + "      \"hashtags\":[],"
        + "      \"user_mentions\":[]"
        + "   },\n"
        + "   \"coordinates\":null,"
        + "   \"retweet_count\":0,\n"
        + "   \"favorite_count\":0,\n"
        + "   \"favorited\":false,\n"
        + "   \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDAO spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

    //mock parseResponse
    doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
    Tweet tweet = spyDao.create(post);
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void postTweet() {
    Tweet post = buildTweet();

    Tweet responseTweet = newDao.create(post);

    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));

    id = responseTweet.getIdStr();
  }

  @Test
  public void deleteTweet() {
    if (id == null) {
      time = System.currentTimeMillis();
      Tweet post = buildTweet();
      Tweet responseTweet = newDao.create(post);
      id = responseTweet.getIdStr();
    }
    Tweet responseTweet = newDao.deleteById(id);

    assertEquals(text, responseTweet.getText());
    assertNotNull(responseTweet.getCoordinates());
    assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

    assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));
  }
}
