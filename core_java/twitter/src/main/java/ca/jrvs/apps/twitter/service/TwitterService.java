package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.StringUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private final CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    // Business logic
    validatePostTweet(tweet);

    return (Tweet) dao.create(tweet);
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    // Business logic
    validateId(id);
    validateFields(fields);

    Tweet tweet = (Tweet) dao.findById(id);

    // Modify Tweet obj & validate fields
    for (String field : fields) {
      switch (field) {
        case "created_at":
          tweet.setCreated_at(null);
          break;
        case "id":
          tweet.setId(null);
          break;
        case "id_str":
          tweet.setIdStr(null);
          break;
        case "text":
          tweet.setText(null);
          break;
        case "entities":
          tweet.setEntities(null);
          break;
        case "coordinates":
          tweet.setCoordinates(null);
          break;
        case "retweet_count":
          tweet.setRetweetCount(null);
          break;
        case "favorite_count":
          tweet.setFavoriteCount(null);
          break;
        case "favorited":
          tweet.setFavorited(null);
          break;
        case "retweeted":
          tweet.setRetweeted(null);
          break;
        default:
          throw new IllegalArgumentException();
      }
    }

    return tweet;
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> lst = new ArrayList<>();

    for (String id : ids) {
      // Business logic
      validateId(id);
    }

    for (String id : ids) {
      // Delete and add to lst
      lst.add((Tweet) dao.deleteById(id));
    }
    return lst;
  }

  private void validatePostTweet(Tweet tweet) {
    //check txt len and coords range
    Double lon = tweet.getCoordinates().getCoordinates().get(0);
    Double lat = tweet.getCoordinates().getCoordinates().get(1);
    if (tweet.getText().length() > 140 || lat > 90.0 || lat < -90.0 || lon > 180.0
        || lon < -180.0) {
      throw new IllegalArgumentException();
    }
  }

  private void validateId(String id) {
    if (StringUtil.isEmpty(id)) {
      throw new IllegalArgumentException();
    }
    try {
      new BigInteger(id);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }

  private void validateFields(String[] fields) {
    if (fields == null) {
      throw new IllegalArgumentException();
    }
  }
}
