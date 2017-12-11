package gargobre.server.page;

import java.util.Map;

public abstract class HtmlPage extends Page {

  public abstract byte[] bytes(String path, Map<String, Object> params);

}
