package gargobre.server.httpserver;

public abstract class HttpServlet {

  public abstract HttpResponse handler(HttpRequest request);

}
