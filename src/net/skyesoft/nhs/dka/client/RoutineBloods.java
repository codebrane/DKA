/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

import java.util.Vector;

public class RoutineBloods extends Sink {
  private static final String PAGE_ID = "routinebloods";
  private static final String NAME_IN_LIST = "Routine bloods";
  private static final String DESCRIPTION = "Routine bloods";

  private static final int INDEX_tbHour = 0;
  private static final int INDEX_tbUEs = 1;
  private static final int INDEX_tbLabglu = 2;
  private static final int INDEX_tbVenabgs = 3;
  private static final int INDEX_tbBm = 4;
  private static final int INDEX_tbBicarbonate = 5;

  private int currentHour = 1;
  private int currentUEs = 1;
  private int currentLabglu = 1;
  private int currentVenabgs = 1;
  private int currentBm = 1;
  private int currentBicarbonate = 1;
  
  final Button newRowButton = new Button("+");
  final Button deleteRowButton = new Button("-");
  final FlexTable table = new FlexTable();
  final FormPanel form = new FormPanel();
  Vector textBoxes = null;

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new RoutineBloods();
      }
    };
  }

  public RoutineBloods() {
    textBoxes = new Vector();
    
    form.setAction(Defines.FORM_HANDLER);
    form.setMethod(FormPanel.METHOD_POST);

    newRowButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        generateRow();
      }
    });

    deleteRowButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        deleteRow();
      }
    });

    form.addFormHandler(new FormHandler() {
        public void onSubmitComplete(FormSubmitCompleteEvent event) {
        }

        public void onSubmit(FormSubmitEvent event) {
        }
    });

    Hidden hiddentTableName = new Hidden();
    hiddentTableName.setName("TableName");
    hiddentTableName.setValue(PAGE_ID);
    table.setWidget(100, 100, hiddentTableName);

    Hidden hiddentStudyNo = new Hidden();
    hiddentStudyNo.setName("StudyNo");
    hiddentStudyNo.setValue(studyNo);
    table.setWidget(101, 101, hiddentStudyNo);

    table.setText(0, 0, "Hour");
    table.setText(0, 1, "UEs");
    table.setText(0, 2, "labglu");
    table.setText(0, 3, "venabgs");
    table.setText(0, 4, "bm");
    table.setText(0, 5, "bicarbonate");

    generateRow();

    form.add(table);

    initWidget(form);
  }

  private void reset() {
    currentHour = 1;
    currentUEs = 1;
    currentLabglu = 1;
    currentVenabgs = 1;
    currentBm = 1;
    currentBicarbonate = 1;
    textBoxes.clear();
    table.clear();
  }

  private void generateRow() {
    TextBox[] rowOfTextBoxes = new TextBox[6];
    
    TextBox tbHour = new TextBox();
    tbHour.setName("Hour_" + currentHour);
    table.setWidget(currentHour, 0, tbHour);
    rowOfTextBoxes[INDEX_tbHour] = tbHour;
    currentHour++;

    TextBox tbUEs = new TextBox();
    tbUEs.setName("UEs_" + currentUEs);
    table.setWidget(currentUEs, 1, tbUEs);
    rowOfTextBoxes[INDEX_tbUEs] = tbUEs;
    currentUEs++;

    TextBox tbLabglu = new TextBox();
    tbLabglu.setName("Labglu_" + currentLabglu);
    table.setWidget(currentLabglu, 2, tbLabglu);
    rowOfTextBoxes[INDEX_tbLabglu] = tbLabglu;
    currentLabglu++;

    TextBox tbVenabgs = new TextBox();
    tbVenabgs.setName("Venabgs_" + currentVenabgs);
    table.setWidget(currentVenabgs, 3, tbVenabgs);
    rowOfTextBoxes[INDEX_tbVenabgs] = tbVenabgs;
    currentVenabgs++;

    TextBox tbBm = new TextBox();
    tbBm.setName("Bm_" + currentBm);
    table.setWidget(currentBm, 4, tbBm);
    rowOfTextBoxes[INDEX_tbBm] = tbBm;
    currentBm++;

    TextBox tbBicarbonate = new TextBox();
    tbBicarbonate.setName("Bicarbonate_" + currentBicarbonate);
    table.setWidget(currentBicarbonate, 5, tbBicarbonate);
    rowOfTextBoxes[INDEX_tbBicarbonate] = tbBicarbonate;
    currentBicarbonate++;

    table.setWidget(currentBicarbonate - 1, 6, newRowButton);
    table.setWidget(currentBicarbonate - 1, 7, deleteRowButton);

    textBoxes.add(rowOfTextBoxes);
  }

  private void deleteRow() {
    currentHour--;
    currentUEs--;
    currentLabglu--;
    currentVenabgs--;
    currentBm--;
    currentBicarbonate--;

    table.clearCell(currentHour, 0);
    table.clearCell(currentUEs, 1);
    table.clearCell(currentLabglu, 2);
    table.clearCell(currentVenabgs, 3);
    table.clearCell(currentBm, 4);
    table.clearCell(currentBicarbonate, 5);

    table.clearCell(currentHour, 6);
    table.clearCell(currentHour, 7);

    table.setWidget(currentHour - 1, 6, newRowButton);
    table.setWidget(currentHour - 1, 7, deleteRowButton);

    // Remove the last row of text boxes added
    textBoxes.remove(currentHour - 1);
  }

  public void loadPreviousData(String studyNo) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, Defines.FORM_HANDLER + "?mode=getmultirows&StudyNo=" + studyNo + "&TableName=" + PAGE_ID + "&" +
                                                Utils.DATE_PARAM + "=" + Utils.getDate());
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(null, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to load the data for IVFluids : " + re.getMessage());
    }
  }

  /* To avoid network delay problems, we'll wrap the real save operation with a reset of the data
   * in the database.
   */
  public void save() {
    // Clear out any data associated with the particular study no
    String postData = "mode=rawsql&sql=delete from routinebloods where StudyNo='" + studyNo + "'";

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new ResetDBDataResponseTextHandler());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for RoutineBloods : " + re.getMessage());
    }
  }
  class ResetDBDataResponseTextHandler implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for RoutineBloods : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      realSave();
    }
  }

  public void realSave() {
    String postData = "mode=putmultirows&";
    postData += Defines.TABLE_NAME_KEY + "=" + PAGE_ID + "&";
    postData += Utils.DATE_PARAM + "=" + Utils.getDate() + "&";
    postData += "data=";

    // Loop through the text boxes
    for (int c=0; c < textBoxes.size(); c++) {
      postData += Defines.STUDY_NO_KEY + "-------" + studyNo + "@@@@@@@";

      TextBox[] rowOfTextBoxes = (TextBox[])textBoxes.get(c);

      for (int cc=0; cc < rowOfTextBoxes.length; cc++) {
        // Get the real name of the text box as known in the database table
        String tbName = rowOfTextBoxes[cc].getName().split("_")[0];

        postData += tbName + "-------" + rowOfTextBoxes[cc].getText();

        if (cc < (rowOfTextBoxes.length - 1))
          postData += "@@@@@@@";
      }

      if (c < (textBoxes.size() - 1))
        postData += "VVVVVVVVVVVVVVVV";
    }

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new DummyResponseTextHandler());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for RoutineBloods : " + re.getMessage());
    }
  }
  class DummyResponseTextHandler implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for RoutineBloods : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {}
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for RoutineBloods : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      /*
       * The data is coming back as multiple lines of WIDGET_NAME VALUE pairs delimited as:
       * WIDGET_NAME-------VALUE@@@@@@@ ... VVVVVVVVVVVVVVVVWIDGET_NAME-------VALUE@@@@@@@
       */
      String[] rows = response.getText().split("VVVVVVVVVVVVVVVV");

      // Clear out all existing data as we have a new set
      reset();

      for (int rowCount=0; rowCount < rows.length; rowCount++) {
        String[] namesAndValues = rows[rowCount].split("@@@@@@@");

        // Get a new row of text boxes ready
        generateRow();

        // textBoxes is 0 based. currentHour = 1 when the first row is added, then currentHour is incremented
        TextBox[] rowOfTextBoxes = (TextBox[])textBoxes.get(currentHour - 2);

        /* For some reason, when a TextBox is added to the table, it seems to be a copy and
         * is not linked to the original. So modifying the original, in the rowOfTextBoxes
         * array won't affect the TextBox that's in the table. So you have to modify the
         * original in the rowOfTextBoxes array, then re-add it to the table at the original
         * position.
         */
        for (int c=0; c < namesAndValues.length; c++) {
          String[] parts = namesAndValues[c].split("-------");

          for (int cc=0; cc < parts.length; cc++) {
            if (parts[0].equalsIgnoreCase("Hour")) {
              rowOfTextBoxes[INDEX_tbHour].setText(parts[1]);
              table.setWidget(currentHour - 1, 0, rowOfTextBoxes[INDEX_tbHour]);
            }
            if (parts[0].equalsIgnoreCase("UEs")) {
              rowOfTextBoxes[INDEX_tbUEs].setText(parts[1]);
              table.setWidget(currentUEs - 1, 1, rowOfTextBoxes[INDEX_tbUEs]);
            }
            if (parts[0].equalsIgnoreCase("Labglu")) {
              rowOfTextBoxes[INDEX_tbLabglu].setText(parts[1]);
              table.setWidget(currentLabglu - 1, 2, rowOfTextBoxes[INDEX_tbLabglu]);
            }
            if (parts[0].equalsIgnoreCase("Venabgs")) {
              rowOfTextBoxes[INDEX_tbVenabgs].setText(parts[1]);
              table.setWidget(currentVenabgs - 1, 3, rowOfTextBoxes[INDEX_tbVenabgs]);
            }
            if (parts[0].equalsIgnoreCase("Bm")) {
              rowOfTextBoxes[INDEX_tbBm].setText(parts[1]);
              table.setWidget(currentBm - 1, 4, rowOfTextBoxes[INDEX_tbBm]);
            }
            if (parts[0].equalsIgnoreCase("Bicarbonate")) {
              rowOfTextBoxes[INDEX_tbBicarbonate].setText(parts[1]);
              table.setWidget(currentBicarbonate - 1, 5, rowOfTextBoxes[INDEX_tbBicarbonate]);
            }
          }
        }
      }
    }
  } // class PreviousDataLoader implements ResponseTextHandler
}
