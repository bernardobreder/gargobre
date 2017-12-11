package gargobre.server.code;

import java.nio.charset.StandardCharsets;

public class StringUTF {

  private final String string;

  private byte[] bytes;

  /**
   * @param string
   */
  public StringUTF(String string) {
    this.string = string;
    this.bytes = string.getBytes(StandardCharsets.UTF_8);
  }

  public String string() {
    return string;
  }

  public byte[] bytes() {
    return bytes;
  }

}
