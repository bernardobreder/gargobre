import java.io.IOException;
import java.nio.file.Paths;

import gargobre.server.httpserver.MyHttpServer;
import gargobre.server.page.TestHtmlPage;
import gargobre.server.servlet.PageServlet;

public class Main {

  public static void main(String[] args) throws IOException {
    // https://stackoverflow.com/questions/2308479/simple-java-https-server
    MyHttpServer server = new MyHttpServer(8000);
    server.addFolder("/pub", Paths.get("pub"));
    server.addServlet("/test", new PageServlet(new TestHtmlPage()));
    server.start();
  }

}
