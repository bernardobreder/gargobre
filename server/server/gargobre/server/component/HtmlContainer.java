package gargobre.server.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gargobre.server.code.HtmlCode;
import gargobre.server.code.StringUTF;

public class HtmlContainer extends HtmlComponent {

  private final List<HtmlComponent> children;

  public HtmlContainer() {
    this(new ArrayList<>());
  }

  public HtmlContainer(HtmlComponent... components) {
    this(new ArrayList<>(Arrays.asList(components)));
  }

  public HtmlContainer(List<HtmlComponent> children) {
    this.children = children;
  }

  public HtmlContainer add(HtmlComponent child) {
    children.add(child);
    return this;
  }

  public HtmlContainer add(StringUTF child) {
    return add(new HtmlText(child));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(HtmlCode code) {
    for (int i = 0; i < children.size(); i++) {
      children.get(i).write(code);
    }
  }

}
