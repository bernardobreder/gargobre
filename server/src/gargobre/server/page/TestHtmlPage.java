package gargobre.server.page;

import gargobre.server.component.HtmlContainer;
import gargobre.server.component.HtmlH1;
import gargobre.server.component.HtmlText;

public class TestHtmlPage extends BaseHtmlPage {

  /**
   * {@inheritDoc}
   */
  @Override
  public String title() {
    return "Test page";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlContainer body() {
    return new HtmlContainer(new HtmlH1(new HtmlContainer(new HtmlText("Hello"),
      new HtmlText("World"))));
  }

}
