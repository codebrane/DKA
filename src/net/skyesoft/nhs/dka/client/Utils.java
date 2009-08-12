/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.ListBox;

import java.util.Date;

public class Utils {
  public static final String DATE_PARAM = "date";
  
  public static int getListBoxSelectedIndex(ListBox lb, String value) {
    for (int c=0; c < lb.getItemCount(); c++) {
      if (lb.getValue(c).equals(value))
        return c;
    }
    return 0;
  }

  public static String getDate() {
    return new Date().toString();
  }
}
