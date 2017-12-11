package gargobre.server.servlet;

import java.util.HashMap;

import gargobre.server.httpserver.HttpRequest;
import gargobre.server.httpserver.HttpResponse;
import gargobre.server.httpserver.HttpServlet;
import gargobre.server.page.HtmlPage;

public class PageServlet extends HttpServlet {

  private HtmlPage page;

  public PageServlet(HtmlPage page) {
    this.page = page;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HttpResponse handler(HttpRequest request) {
    byte[] bytes = page.bytes(request.path(), new HashMap<String, Object>());
    return new HttpResponse(200, " text/html; charset=utf-8", bytes);
  }

}
