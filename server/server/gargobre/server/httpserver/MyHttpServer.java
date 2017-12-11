package gargobre.server.httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class MyHttpServer {

  private final Map<String, HttpServlet> servletMap = new Hashtable<>();

  private Map.Entry<String, Path> resourcePathMap = null;

  public MyHttpServer(int port) {
  }

  public void addServlet(String path, HttpServlet servlet) {
    servletMap.put(path, servlet);
  }

  public void addFolder(String path, Path dir) {
    resourcePathMap = new AbstractMap.SimpleEntry<>(path + "/", dir);
  }

  public void start() throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/", t -> {
      handler(t);
    });
    server.setExecutor(Executors.newCachedThreadPool());
    server.start();
  }

  protected void handler(HttpExchange t) throws IOException {
    String query = t.getRequestURI().getPath();
    HttpServlet servlet = servletMap.get(query);
    if (servlet == null) {
      if (resourcePathMap != null) {
        if (query.startsWith(query)) {
          Path path = resourcePathMap.getValue().resolve(query.substring(
            resourcePathMap.getKey().length()));
          handlerResource(t, path);
        }
      }
      else {
        handlerPageNotFound(t);
      }
    }
    else {
      handlerServlet(t, servlet);
    }
  }

  protected void handlerServlet(HttpExchange t, HttpServlet servlet)
    throws IOException {
    String query = t.getRequestURI().getPath();
    HttpResponse response = servlet.handler(new HttpRequest(query));
    t.getResponseHeaders().set("Content-Type", response.contentType());
    t.sendResponseHeaders(response.code(), response.bytes().length);
    try (OutputStream os = t.getResponseBody()) {
      os.write(response.bytes());
    }
  }

  protected void handlerPageNotFound(HttpExchange t) throws IOException {
    String response = "<h1>Page not found</h1>";
    t.sendResponseHeaders(400, response.length());
    OutputStream os = t.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  protected void handlerResource(HttpExchange t, Path path) throws IOException {
    String response = "<h1>" + path.toString() + "</h1>";
    t.sendResponseHeaders(400, response.length());
    OutputStream os = t.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

}
