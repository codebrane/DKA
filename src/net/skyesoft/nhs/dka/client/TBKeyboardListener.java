/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;

public class TBKeyboardListener extends KeyboardListenerAdapter {
  Button buttonToEnable = null;

  public TBKeyboardListener(Button buttonToEnable) {
    this.buttonToEnable = buttonToEnable;
  }

  public void onKeyUp(Widget widget, char c, int i) {
    buttonToEnable.setEnabled(true);
  }

  public void onKeyPress(Widget widget, char c, int i) {
    buttonToEnable.setEnabled(true);
  }

  public void onKeyDown(Widget widget, char c, int i) {
    buttonToEnable.setEnabled(true);
  }
}
