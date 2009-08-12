/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.HTTPRequest;

public class About extends Sink {
  final HTML mainHTML = new HTML();

  public static SinkInfo init() {
    return new SinkInfo("About", "About Audit of the use of New DKA protocol") {
      public Sink createInstance() {
        return new About();
      }
    };
  }

  public About() {
    mainHTML.setStyleName("about");
    HTTPRequest.asyncGet("about.html", new HTMLDisplayer());
    initWidget(mainHTML);
  }

  class HTMLDisplayer implements ResponseTextHandler {
    public void onCompletion(String responseText) {
      mainHTML.setHTML(responseText);
    }
  }
}
