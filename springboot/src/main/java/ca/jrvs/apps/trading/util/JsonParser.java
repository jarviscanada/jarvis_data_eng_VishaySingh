package ca.jrvs.apps.trading.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonParser {

  /**
   * Parse JSON String to an object
   *
   * @param json
   * @param myClass
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class myClass) throws IOException {
    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
        false); //ignore the robust obj
    return (T) m.readValue(json, myClass);
  }

}
