package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigInteger;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})
public class Tweet {

  @JsonProperty("created_at")
  private String created_at;

  @JsonProperty("id")
  private BigInteger id;

  @JsonProperty("id_str")
  private String idStr;

  @JsonProperty("text")
  private String text;

  @JsonProperty("entities")
  private Entities entities = null;

  @JsonProperty("coordinates")
  private Coordinates coordinates = null;

  @JsonProperty("retweet_count")
  private Integer retweetCount;

  @JsonProperty("favorite_count")
  private Integer favoriteCount;

  @JsonProperty("favorited")
  private Boolean favorited;

  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonProperty("created_at")
  public String getCreated_at() {
    return created_at;
  }

  @JsonProperty("created_at")
  public void setCreated_at(String created) {
    created_at = created;
  }

  @JsonProperty("id")
  public BigInteger getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(BigInteger id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idString) {
    idStr = idString;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String txt) {
    text = txt;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities ent) {
    entities = ent;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates coords) {
    coordinates = coords;
  }

  @JsonProperty("retweet_count")
  public Integer getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(Integer retweetCnt) {
    retweetCount = retweetCnt;
  }

  @JsonProperty("favorite_count")
  public Integer getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(Integer favoriteCnt) {
    favoriteCount = favoriteCnt;
  }

  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(Boolean fav) {
    favorited = fav;
  }

  @JsonProperty("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(Boolean retweet) {
    retweeted = retweet;
  }
}
