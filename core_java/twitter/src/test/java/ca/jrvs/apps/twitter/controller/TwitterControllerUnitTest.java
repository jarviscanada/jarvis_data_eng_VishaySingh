package ca.jrvs.apps.twitter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  //private static String testId = "1537516191467683841"; //temporary tweet id
  //can replace with env var:
  private static final String testId = System.getenv("tweetId");
  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet() {
    when(service.postTweet(any())).thenReturn(new Tweet());
    controller.postTweet(new String[]{"post", "text", "1.0:-1.0"});
  }

  @Test
  public void showTweet() {
    when(service.showTweet(any(), any())).thenReturn(new Tweet());
    controller.showTweet(new String[]{"show", testId});
  }

  @Test
  public void deleteTweet() {
    //Note that this test only works if ran after showTweet,
    //for simplicity
    when(service.deleteTweets(any())).thenReturn(any());
    controller.deleteTweet(new String[]{"delete", testId});
  }
}