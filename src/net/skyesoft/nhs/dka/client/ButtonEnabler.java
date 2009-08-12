/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class ButtonEnabler implements ChangeListener {
  Button buttonToEnable = null;

  public ButtonEnabler(Button buttonToEnable) {
    this.buttonToEnable = buttonToEnable;
  }

  public void onChange(Widget widget) {
    buttonToEnable.setEnabled(true);
  }
}
