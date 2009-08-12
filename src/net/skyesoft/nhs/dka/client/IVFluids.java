/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

import java.util.Vector;

public class IVFluids extends Sink {
  private static final String PAGE_ID = "ivfluids";
  private static final String NAME_IN_LIST = "IV Fluids";
  private static final String DESCRIPTION = "IV Fluids";

  private static final int INDEX_tbHour = 0;
  private static final int INDEX_tbNaCl = 1;
  private static final int INDEX_tbNaClRateCorrect = 2;
  private static final int INDEX_tbBMLessThan14 = 3;
  private static final int INDEX_tbTenPercentDextrose = 4;
  private static final int INDEX_tbDextroseRateCorrect = 5;
  private static final int INDEX_tbKPlus = 6;
  private static final int INDEX_lbKPlusReplacement = 7;
  private static final int INDEX_tbInsulinUnitsPerHour = 8;
  private static final int INDEX_tbVolumeInfused = 9;

  private int currentHour = 1;
  private int currentNaCl = 1;
  private int currentNaClRateCorrect = 1;
  private int currentBMLessThan14 = 1;
  private int current10PercentDextrose = 1;
  private int currentDextroseRateCorrect = 1;
  private int currentKPlus = 1;
  private int currentKPlusReplacement = 1;
  private int currentInsulinUnitsPerHour = 1;
  private int currentVolumeInfused = 1;
  final FlexTable table = new FlexTable();
  final Button newRowButton = new Button("+");
  final Button deleteRowButton = new Button("-");
  final FormPanel form = new FormPanel();
  Vector textBoxes = null;

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new IVFluids();
      }
    };
  }

  public IVFluids() {
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
    table.setText(0, 1, "NaCl");
    table.setText(0, 2, "NaClratecorrect");
    table.setText(0, 3, "BM<14");
    table.setText(0, 4, "10% dextrose");
    table.setText(0, 5, "Dextrose ratecorrect");
    table.setText(0, 6, "K+ (laboratory value)");
    table.setText(0, 7, "K+ replacement");
    table.setText(0, 8, "Insulin units/hr");
    table.setText(0, 9, "Volume infused ml/hr");

    generateRow();

    form.add(table);

    initWidget(form);
  }

  private void reset() {
    currentHour = 1;
    currentNaCl = 1;
    currentNaClRateCorrect = 1;
    currentBMLessThan14 = 1;
    current10PercentDextrose = 1;
    currentDextroseRateCorrect = 1;
    currentKPlus = 1;
    currentKPlusReplacement = 1;
    currentInsulinUnitsPerHour = 1;
    currentVolumeInfused = 1;
    textBoxes.clear();
    table.clear();
  }

  private void generateRow() {
    FocusWidget[] rowOfTextBoxes = new FocusWidget[10];

    TextBox tbHour = new TextBox();
    tbHour.setName("Hour_" + currentHour);
    table.setWidget(currentHour, 0, tbHour);
    rowOfTextBoxes[INDEX_tbHour] = tbHour;
    currentHour++;

    TextBox tbNaCl = new TextBox();
    tbNaCl.setName("NaCl_" + currentNaCl);
    table.setWidget(currentNaCl, 1, tbNaCl);
    rowOfTextBoxes[INDEX_tbNaCl] = tbNaCl;
    currentNaCl++;

    TextBox tbNaClRateCorrect = new TextBox();
    tbNaClRateCorrect.setName("NaClRateCorrect_" + currentNaClRateCorrect);
    table.setWidget(currentNaClRateCorrect, 2, tbNaClRateCorrect);
    rowOfTextBoxes[INDEX_tbNaClRateCorrect] = tbNaClRateCorrect;
    currentNaClRateCorrect++;

    TextBox tbBMLessThan14 = new TextBox();
    tbBMLessThan14.setName("BMLessThan14_" + currentBMLessThan14);
    table.setWidget(currentBMLessThan14, 3, tbBMLessThan14);
    rowOfTextBoxes[INDEX_tbBMLessThan14] = tbBMLessThan14;
    currentBMLessThan14++;

    TextBox tbTenPercentDextrose = new TextBox();
    tbTenPercentDextrose.setName("TenPercentDextrose_" + current10PercentDextrose);
    table.setWidget(current10PercentDextrose, 4, tbTenPercentDextrose);
    rowOfTextBoxes[INDEX_tbTenPercentDextrose] = tbTenPercentDextrose;
    current10PercentDextrose++;

    TextBox tbDextroseRateCorrect = new TextBox();
    tbDextroseRateCorrect.setName("DextroseRateCorrect_" + currentDextroseRateCorrect);
    table.setWidget(currentDextroseRateCorrect, 5, tbDextroseRateCorrect);
    rowOfTextBoxes[INDEX_tbDextroseRateCorrect] = tbDextroseRateCorrect;
    currentDextroseRateCorrect++;

    TextBox tbKPlus = new TextBox();
    tbKPlus.setName("KPlus_" + currentKPlus);
    table.setWidget(currentKPlus, 6, tbKPlus);
    rowOfTextBoxes[INDEX_tbKPlus] = tbKPlus;
    currentKPlus++;

    ListBox lbKPlusReplacement = new ListBox();
    lbKPlusReplacement.setName("KPlusReplacement_" + currentKPlusReplacement);
    lbKPlusReplacement.addItem("None", "none");
    lbKPlusReplacement.addItem("20 mmol/l", "20mmol/l");
    lbKPlusReplacement.addItem("40 mmol/l", "40mmol/l");
    table.setWidget(currentKPlusReplacement, 7, lbKPlusReplacement);
    rowOfTextBoxes[INDEX_lbKPlusReplacement] = lbKPlusReplacement;
    currentKPlusReplacement++;

    TextBox tbInsulinUnitsPerHour = new TextBox();
    tbInsulinUnitsPerHour.setName("InsulinUnitsPerHour_" + currentInsulinUnitsPerHour);
    table.setWidget(currentInsulinUnitsPerHour, 8, tbInsulinUnitsPerHour);
    rowOfTextBoxes[INDEX_tbInsulinUnitsPerHour] = tbInsulinUnitsPerHour;
    currentInsulinUnitsPerHour++;

    TextBox tbVolumeInfused = new TextBox();
    tbVolumeInfused.setName("VolumeInfused_" + currentVolumeInfused);
    table.setWidget(currentVolumeInfused, 9, tbVolumeInfused);
    rowOfTextBoxes[INDEX_tbVolumeInfused] = tbVolumeInfused;
    currentVolumeInfused++;

    table.setWidget(currentVolumeInfused - 1, 10, newRowButton);
    table.setWidget(currentVolumeInfused - 1, 11, deleteRowButton);

    textBoxes.add(rowOfTextBoxes);
  }

  private void deleteRow() {
    currentHour--;
    currentNaCl--;
    currentNaClRateCorrect--;
    currentBMLessThan14--;
    current10PercentDextrose--;
    currentDextroseRateCorrect--;
    currentKPlus--;
    currentKPlusReplacement--;
    currentInsulinUnitsPerHour--;
    currentVolumeInfused--;

    table.clearCell(currentHour, 0);

    table.clearCell(currentNaCl, 1);

    table.clearCell(currentNaClRateCorrect, 2);

    table.clearCell(currentBMLessThan14, 3);

    table.clearCell(current10PercentDextrose, 4);

    table.clearCell(currentDextroseRateCorrect, 5);

    table.clearCell(currentKPlus, 6);

    table.clearCell(currentKPlusReplacement, 7);

    table.clearCell(currentInsulinUnitsPerHour, 8);

    table.clearCell(currentVolumeInfused, 9);

    table.clearCell(currentHour, 10);
    table.clearCell(currentHour, 11);

    table.setWidget(currentHour - 1, 10, newRowButton);
    table.setWidget(currentHour - 1, 11, deleteRowButton);

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
    String postData = "mode=rawsql&sql=delete from ivfluids where StudyNo='" + studyNo + "'";
    
    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new ResetDBDataResponseTextHandler());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for IVFluids : " + re.getMessage());
    }
  }
  class ResetDBDataResponseTextHandler implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to save the data for IVFluids : " + exception.getMessage());
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
      
      FocusWidget[] rowOfTextBoxes = (FocusWidget[])textBoxes.get(c);

      for (int cc=0; cc < rowOfTextBoxes.length; cc++) {
        // Get the real name of the text box as known in the database table
        String tbName = null;
        if (rowOfTextBoxes[cc] instanceof TextBox) {
          tbName = ((TextBox)(rowOfTextBoxes[cc])).getName().split("_")[0];
          postData += tbName + "-------" + ((TextBox)(rowOfTextBoxes[cc])).getText();
        }
        if (rowOfTextBoxes[cc] instanceof ListBox) {
          tbName = ((ListBox)(rowOfTextBoxes[cc])).getName().split("_")[0];
          postData += tbName + "-------" + ((ListBox)(rowOfTextBoxes[cc])).getValue(((ListBox)(rowOfTextBoxes[cc])).getSelectedIndex());
        }

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
      Window.alert("Failed to save the data for IVFluids : " + re.getMessage());
    }
  }
  class DummyResponseTextHandler implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for IVFluids : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {}
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for IVFluids : " + exception.getMessage());
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
        FocusWidget[] rowOfTextBoxes = (FocusWidget[])textBoxes.get(currentHour - 2);

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
              ((TextBox)(rowOfTextBoxes[INDEX_tbHour])).setText(parts[1]);
              table.setWidget(currentHour - 1, 0, rowOfTextBoxes[INDEX_tbHour]);
            }
            if (parts[0].equalsIgnoreCase("NaCl")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbNaCl])).setText(parts[1]);
              table.setWidget(currentNaCl - 1, 1, rowOfTextBoxes[INDEX_tbNaCl]);
            }
            if (parts[0].equalsIgnoreCase("NaClRateCorrect")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbNaClRateCorrect])).setText(parts[1]);
              table.setWidget(currentNaClRateCorrect - 1, 2, rowOfTextBoxes[INDEX_tbNaClRateCorrect]);
            }
            if (parts[0].equalsIgnoreCase("BMLessThan14")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbBMLessThan14])).setText(parts[1]);
              table.setWidget(currentBMLessThan14 - 1, 3, rowOfTextBoxes[INDEX_tbBMLessThan14]);
            }
            if (parts[0].equalsIgnoreCase("TenPercentDextrose")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbTenPercentDextrose])).setText(parts[1]);
              table.setWidget(current10PercentDextrose - 1, 4, rowOfTextBoxes[INDEX_tbTenPercentDextrose]);
            }
            if (parts[0].equalsIgnoreCase("DextroseRateCorrect")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbDextroseRateCorrect])).setText(parts[1]);
              table.setWidget(currentDextroseRateCorrect - 1, 5, rowOfTextBoxes[INDEX_tbDextroseRateCorrect]);
            }
            if (parts[0].equalsIgnoreCase("KPlus")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbKPlus])).setText(parts[1]);
              table.setWidget(currentKPlus - 1, 6, rowOfTextBoxes[INDEX_tbKPlus]);
            }
            if (parts[0].equalsIgnoreCase("KPlusReplacement")) {
              ((ListBox)(rowOfTextBoxes[INDEX_lbKPlusReplacement])).setSelectedIndex(Utils.getListBoxSelectedIndex(((ListBox)(rowOfTextBoxes[INDEX_lbKPlusReplacement])), parts[1]));
              table.setWidget(currentKPlusReplacement - 1, 7, rowOfTextBoxes[INDEX_lbKPlusReplacement]);
            }
            if (parts[0].equalsIgnoreCase("InsulinUnitsPerHour")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbInsulinUnitsPerHour])).setText(parts[1]);
              table.setWidget(currentInsulinUnitsPerHour - 1, 8, rowOfTextBoxes[INDEX_tbInsulinUnitsPerHour]);
            }
            if (parts[0].equalsIgnoreCase("VolumeInfused")) {
              ((TextBox)(rowOfTextBoxes[INDEX_tbVolumeInfused])).setText(parts[1]);
              table.setWidget(currentVolumeInfused - 1, 9, rowOfTextBoxes[INDEX_tbVolumeInfused]);
            }
          }
        }
      }
    }
  } // class PreviousDataLoader implements ResponseTextHandler
}
