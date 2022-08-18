package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.StringUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private final Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      usagePostError();
    }

    String text = args[1];
    String coords = args[2];
    String[] coordArray = coords.split(COORD_SEP);
    if (coordArray.length != 2 || StringUtil.isEmpty(text)) {
      usagePostError();
    }
    Double lat = null;
    Double lon = null;
    try {
      lat = Double.parseDouble(coordArray[0]);
      lon = Double.parseDouble(coordArray[1]);
    } catch (NumberFormatException e) {
      usagePostError();
    }

    Tweet post = TweetUtil.buildTweet(text, lat, lon);
    return service.postTweet(post);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2 || args.length > 3) {
      usageShowError();
    }

    String id = args[1];
    String field = "";
    String[] fields = {};
    if (args.length == 3) {
      field = args[2];
      if (StringUtil.isEmpty(field)) {
        usageShowError();
      }
      fields = field.split(COMMA);
    }
    if (StringUtil.isEmpty(id)) {
      usageShowError();
    }

    return service.showTweet(id, fields);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      usageDeleteError();
    }

    String[] ids = args[1].split(COMMA);
    if (ids.length == 0) {
      usageDeleteError();
    }

    return service.deleteTweets(ids);
  }

  private void usagePostError() {
    throw new IllegalArgumentException(
        "USAGE: TwitterCLIApp post \"text\" \"latitude:longitude\""
    );
  }

  private void usageShowError() {
    throw new IllegalArgumentException(
        "USAGE: TwitterApp show tweet_id \"field1,fields2,...\""
    );
  }

  private void usageDeleteError() {
    throw new IllegalArgumentException(
        "USAGE: TwitterApp delete \"id1,id2,...\""
    );
  }
}
