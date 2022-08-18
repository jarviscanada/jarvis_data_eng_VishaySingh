package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigInteger;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "name",
    "indices",
    "screen_name",
    "id",
    "id_str"
})
public class UserMention {

  @JsonProperty("name")
  private String name;

  @JsonProperty("indices")
  private List<Integer> indices = null;

  @JsonProperty("screen_name")
  private String screenName;

  @JsonProperty("id")
  private BigInteger id;

  @JsonProperty("id_str")
  private String idStr;

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name1) {
    name = name1;
  }

  @JsonProperty("indices")
  public List<Integer> getIndices() {
    return indices;
  }

  @JsonProperty("indices")
  public void setIndices(List<Integer> indices1) {
    indices = indices1;
  }

  @JsonProperty("screen_name")
  public String getScreenName() {
    return screenName;
  }

  @JsonProperty("screen_name")
  public void setScreenName(String screen) {
    screenName = screen;
  }

  @JsonProperty("id")
  public BigInteger getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(BigInteger id1) {
    id = id1;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idString) {
    idStr = idString;
  }
}
