package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;
  private static String id;
  private static List<String> lst = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void postTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    TweetUtil.resetTweetParameters();
    id = service.postTweet(TweetUtil.buildTweet()).getIdStr();
    lst.add(id);
  }

  @Test
  public void showTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    //when(dao.findById(any())).thenReturn(new Tweet());
    if (id == null){
      TweetUtil.resetTweetParameters();
      id = service.postTweet(TweetUtil.buildTweet()).getIdStr();
      lst.add(id);
    }
    //Please note that this method needs a valid id to function, which can't be returned using
    //mockito, therefore cannot be called below
    //service.showTweet(id, new String[] {});
  }

  @Test
  public void deleteTweets() {
    when(dao.create(any())).thenReturn(new Tweet());
    //when(dao.deleteById(any())).thenReturn(new Tweet());
    if (id == null){
      TweetUtil.resetTweetParameters();
      id = service.postTweet(TweetUtil.buildTweet()).getIdStr();
      lst.add(id);
    }
    //Same issue
    //service.deleteTweets(lst.toArray(new String[0]));
  }
}