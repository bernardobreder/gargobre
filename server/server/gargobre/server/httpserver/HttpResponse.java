package gargobre.server.httpserver;

public class HttpResponse {

  private int status;

  private String contentType;

  private byte[] bytes;

  public HttpResponse(int status, String contentType, byte[] bytes) {
    super();
    this.status = status;
    this.contentType = contentType;
    this.bytes = bytes;
  }

  /**
   * Retorna
   *
   * @return status
   */
  public int code() {
    return status;
  }

  /**
   * Retorna
   *
   * @return contentType
   */
  public String contentType() {
    return contentType;
  }

  /**
   * Retorna
   *
   * @return bytes
   */
  public byte[] bytes() {
    return bytes;
  }

}
