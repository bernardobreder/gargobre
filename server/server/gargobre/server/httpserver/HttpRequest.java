package gargobre.server.httpserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

  private final String path;

  private final Map<String, String> params = new HashMap<String, String>();

  public HttpRequest(String path) {
    super();
    this.path = path;
  }

  /**
   * Retorna
   *
   * @return path
   */
  public String path() {
    return path;
  }

  /**
   * Retorna
   *
   * @return params
   */
  public Map<String, String> params() {
    return params;
  }

}
