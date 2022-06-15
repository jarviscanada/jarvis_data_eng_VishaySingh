package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

  private List<Double> coordinates = null;
  private String type;

  public List<Double> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Double> coords) {
    coordinates = coords;
  }

  public String getType() {
    return type;
  }

  public void setType(String type1) {
    type = type1;
  }
}
