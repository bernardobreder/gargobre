package gargobre.server.component;

import gargobre.server.code.HtmlCode;
import gargobre.server.code.StringUTF;

public class HtmlText extends HtmlComponent {

  private final StringUTF string;

  public HtmlText(String string) {
    this(new StringUTF(string));
  }

  public HtmlText(StringUTF string) {
    this.string = string;
  }

  @Override
  public void write(HtmlCode code) {
    code.write(string);
  }

}
