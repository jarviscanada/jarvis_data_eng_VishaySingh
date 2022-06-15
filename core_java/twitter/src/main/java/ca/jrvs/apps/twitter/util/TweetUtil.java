package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {
  private static long time = System.currentTimeMillis();
  private static String hashtag = "#testing #twitterapi";
  private static String text =
      "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms";
  private static Double lat = 1d;
  private static Double lon = -1d;
  public static Tweet buildTweet() {
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

  public static Tweet buildInvalidTweet(int type) {
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

  public static void resetTweetParameters() {
    time = System.currentTimeMillis();
    hashtag = "#testing #twitterapi";
    text = "@Iostinsanity get better soon " + hashtag + " created at: " + time + "ms";
    lat = 1d;
    lon = -1d;
  }
}
