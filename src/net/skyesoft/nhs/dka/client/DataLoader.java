/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.ui.ListBox;

public class DataLoader implements ResponseTextHandler {
  ListBox listBoxToFill = null;

  public DataLoader(ListBox listBoxToFill) {
    this.listBoxToFill = listBoxToFill;
  }

  public void onCompletion(String responseText) {
    /* Format is:
     * studyno,studyno, ...
     */
    listBoxToFill.clear();

    String[] parts = responseText.split(",");
    for (int c=0; c < parts.length; c++) {
      listBoxToFill.addItem(parts[c], parts[c]);
    }
  }
}
