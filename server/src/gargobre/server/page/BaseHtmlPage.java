package gargobre.server.page;

import java.util.Map;

import gargobre.server.code.HtmlCode;
import gargobre.server.code.StringUTF;
import gargobre.server.component.HtmlContainer;
import gargobre.server.component.HtmlHeader;

public abstract class BaseHtmlPage extends HtmlPage {

  private static final StringUTF CHARSET = new StringUTF(
    "<meta charset=\"utf-8\">");

  private static final StringUTF VIEWPORT = new StringUTF(
    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");

  public abstract HtmlContainer body();

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] bytes(String path, Map<String, Object> params) {
    HtmlCode code = new HtmlCode();
    HtmlContainer head = new HtmlContainer();
    head.add(CHARSET);
    head.add(VIEWPORT);
    head.add(new StringUTF(
      "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\"/>"));
    HtmlContainer body = body();
    body.add(new StringUTF(
      "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\" integrity=\"sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=\" crossorigin=\"anonymous\"></script>"));
    body.add(new StringUTF(
      "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>"));
    body.add(new StringUTF(
      "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>"));
    new HtmlHeader(head, body).write(code);
    return code.bytes();
  }

}
