import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    new MyHttpServer(8000).start();
  }

}
