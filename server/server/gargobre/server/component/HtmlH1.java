package gargobre.server.component;

import gargobre.server.code.HtmlCode;
import gargobre.server.code.StringUTF;

public class HtmlH1 extends HtmlComponent {

  private HtmlComponent child;

  public HtmlH1(HtmlComponent child) {
    this.child = child;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(HtmlCode code) {
    code.write(new StringUTF("<h1>"));
    child.write(code);
    code.write(new StringUTF("</h1>"));
  }

}
