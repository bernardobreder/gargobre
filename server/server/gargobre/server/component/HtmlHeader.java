package gargobre.server.component;

import gargobre.server.code.HtmlCode;
import gargobre.server.code.StringUTF;

public class HtmlHeader extends HtmlComponent {

  private static final StringUTF HTML_OPEN = new StringUTF("<html>");

  private static final StringUTF HTML_CLOSE = new StringUTF("</html>");

  private static final StringUTF HEAD_OPEN = new StringUTF("<head>");

  private static final StringUTF HEAD_CLOSE = new StringUTF("</head>");

  private static final StringUTF BODY_OPEN = new StringUTF("<body>");

  private static final StringUTF BODY_CLOSE = new StringUTF("</body>");

  private final HtmlComponent head;

  private final HtmlComponent body;

  public HtmlHeader(HtmlComponent head, HtmlComponent body) {
    this.head = head;
    this.body = body;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(HtmlCode code) {
    code.write(HTML_OPEN);
    code.write(HEAD_OPEN);
    head.write(code);
    code.write(HEAD_CLOSE);
    code.write(BODY_OPEN);
    body.write(code);
    code.write(BODY_CLOSE);
    code.write(HTML_CLOSE);
  }

}
