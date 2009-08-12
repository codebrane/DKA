/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

public class TransferToSCInsulin extends Sink {
  private static final String PAGE_ID = "transfertoscinsulin";
  private static final String NAME_IN_LIST = "Transfer to s/c insulin";
  private static final String DESCRIPTION = "Transfer to s/c insulin";

  TextBox tbHoursOfIVInsulin = new TextBox();
  TextBox tbOralHypoglycemic_1 = new TextBox();
  TextBox tbOralHypoglycemic_2 = new TextBox();
  TextBox tbOralHypoglycemic_3 = new TextBox();
  TextBox tbTotalDailyDose_1 = new TextBox();
  TextBox tbTotalDailyDose_2 = new TextBox();
  TextBox tbTotalDailyDose_3 = new TextBox();
  TextBox tbInsulin_1 = new TextBox();
  TextBox tbInsulin_2 = new TextBox();
  TextBox tbInsulin_3 = new TextBox();
  TextBox tbInsulin_4 = new TextBox();
  TextBox tbInsulinDose_1 = new TextBox();
  TextBox tbInsulinDose_2 = new TextBox();
  TextBox tbInsulinDose_3 = new TextBox();
  TextBox tbInsulinDose_4 = new TextBox();
  TextBox tbInsulinRegime_1 = new TextBox();
  TextBox tbInsulinRegime_2 = new TextBox();
  TextBox tbInsulinRegime_3 = new TextBox();
  TextBox tbInsulinRegime_4 = new TextBox();
  TextBox tbDevice_1 = new TextBox();
  TextBox tbDevice_2 = new TextBox();
  TextBox tbDevice_3 = new TextBox();
  TextBox tbDevice_4 = new TextBox();
  ListBox lbIVInsulinStopped = new ListBox();
  ListBox lbBicarbonateNormalised = new ListBox();
  ListBox lbUrineKetonesCleared = new ListBox();
  ListBox lbTwoMeals = new ListBox();
  ListBox lbRestartingLongActingInsulinAnalogue = new ListBox();
  CheckBox cbPatientOnLongActingInsulinAnalogue = new CheckBox("Is patient on long acting insulin analogue");
  CheckBox cbLongActingInsulinAnalogueContinued = new CheckBox("Was the long acting insulin analogue continued");

  final FormPanel form = new FormPanel();

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new TransferToSCInsulin();
      }
    };
  }

  public TransferToSCInsulin() {
    form.setAction(Defines.FORM_HANDLER);
    form.setMethod(FormPanel.METHOD_POST);

    form.addFormHandler(new FormHandler() {
        public void onSubmitComplete(FormSubmitCompleteEvent event) {
        }

        public void onSubmit(FormSubmitEvent event) {
        }
    });

    // Text boxes ///////////////////////////////////////////////////////////////////
    tbHoursOfIVInsulin.setName("HoursOfIVInsulin");
    tbOralHypoglycemic_1.setName("OralHypoglycemic_1");
    tbOralHypoglycemic_2.setName("OralHypoglycemic_2");
    tbOralHypoglycemic_3.setName("OralHypoglycemic_3");
    tbTotalDailyDose_1.setName("TotalDailyDose_1");
    tbTotalDailyDose_2.setName("TotalDailyDose_2");
    tbTotalDailyDose_3.setName("TotalDailyDose_3");
    tbInsulin_1.setName("Insulin_1");
    tbInsulin_2.setName("Insulin_2");
    tbInsulin_3.setName("Insulin_3");
    tbInsulin_4.setName("Insulin_4");
    tbInsulinDose_1.setName("InsulinDose_1");
    tbInsulinDose_2.setName("InsulinDose_2");
    tbInsulinDose_3.setName("InsulinDose_3");
    tbInsulinDose_4.setName("InsulinDose_4");
    tbInsulinRegime_1.setName("InsulinRegime_1");
    tbInsulinRegime_2.setName("InsulinRegime_2");
    tbInsulinRegime_3.setName("InsulinRegime_3");
    tbInsulinRegime_4.setName("InsulinRegime_4");
    tbDevice_1.setName("Device_1");
    tbDevice_2.setName("Device_2");
    tbDevice_3.setName("Device_3");
    tbDevice_4.setName("Device_4");
    // //////////////////////////////////////////////////////////////////////////////

    // List boxes ///////////////////////////////////////////////////////////////////
    lbIVInsulinStopped.setName("IVInsulinStopped");
    lbIVInsulinStopped.addItem("30 mins before subcut", "30 mins before subcut");
    lbIVInsulinStopped.addItem("At time of subcut", "at time of subcut");
    lbIVInsulinStopped.addItem("After subcut", "after subcut");

    lbBicarbonateNormalised.setName("BicarbonateNormalised");
    lbBicarbonateNormalised.addItem("Not done", "not done");
    lbBicarbonateNormalised.addItem("Abnormal", "abnormal");
    lbBicarbonateNormalised.addItem("Normal", "normal");

    lbUrineKetonesCleared.setName("UrineKetonesCleared");
    lbUrineKetonesCleared.addItem("None", "none");
    lbUrineKetonesCleared.addItem("Trace", "trace");
    lbUrineKetonesCleared.addItem("Mild +", "mild");
    lbUrineKetonesCleared.addItem("Moderate ++", "moderate");
    lbUrineKetonesCleared.addItem("Severe +++", "severe");
    lbUrineKetonesCleared.addItem("Large ++++", "large");

    lbTwoMeals.setName("TwoMeals");
    lbTwoMeals.addItem("Yes", "yes");
    lbTwoMeals.addItem("No", "no");

    lbRestartingLongActingInsulinAnalogue.setName("RestartingLongActingInsulinAnalogue");
    lbRestartingLongActingInsulinAnalogue.addItem("Never stopped", "never stopped");
    lbRestartingLongActingInsulinAnalogue.addItem("Restarted prior", "restarted prior");
    lbRestartingLongActingInsulinAnalogue.addItem("Restarted after", "restarted after");
    // //////////////////////////////////////////////////////////////////////////////

    // Check boxes ///////////////////////////////////////////////////////////////////
    cbPatientOnLongActingInsulinAnalogue.setName("PatientOnLongActingInsulinAnalogue");
    cbLongActingInsulinAnalogueContinued.setName("LongActingInsulinAnalogueContinued");
    // ///////////////////////////////////////////////////////////////////////////////

    final FlexTable table = new FlexTable();

    Hidden hiddentTableName = new Hidden();
    hiddentTableName.setName("TableName");
    hiddentTableName.setValue(PAGE_ID);
    table.setWidget(100, 100, hiddentTableName);

    Hidden hiddentStudyNo = new Hidden();
    hiddentStudyNo.setName("StudyNo");
    hiddentStudyNo.setValue(studyNo);
    table.setWidget(101, 101, hiddentStudyNo);

    table.setText(0, 0, "Intranvenous insulin therapy");
    table.getFlexCellFormatter().setColSpan(0, 0, 2);

    table.setText(2, 0, "Hours of IV insulin:");
    table.setWidget(2, 1, tbHoursOfIVInsulin);

    table.setText(3, 0, "IV insulin was stopped:");
    table.setWidget(3, 1, lbIVInsulinStopped);

    table.setText(4, 0, "Had the bicarbonate normalised:");
    table.setWidget(4, 1, lbBicarbonateNormalised);

    table.setText(5, 0, "Had the urine ketones cleared:");
    table.setWidget(5, 1, lbUrineKetonesCleared);

    table.setText(5, 0, "Did the patient have two meals prior to discontinuing insulin therapy:");
    table.setWidget(5, 1, lbTwoMeals);

    table.setText(6, 0, "Is the patient on long acting insulin analogue:");
    table.setWidget(6, 1, cbPatientOnLongActingInsulinAnalogue);

    table.setText(7, 0, "Was the long acting insulin analogue continued:");
    table.setWidget(7, 1, cbLongActingInsulinAnalogueContinued);

    table.setText(8, 0, "Restarting long acting insulin analogue:");
    table.setWidget(8, 1, lbRestartingLongActingInsulinAnalogue);

    table.setText(9, 0, "Routine diabetic therapy");
    table.getFlexCellFormatter().setColSpan(9, 0, 2);

    table.setText(11, 0, "Oral hypoglycemic:");
    table.setWidget(12, 0, tbOralHypoglycemic_1);
    table.setWidget(13, 0, tbOralHypoglycemic_2);
    table.setWidget(14, 0, tbOralHypoglycemic_3);

    table.setText(11, 1, "Total daily dose:");
    table.setWidget(12, 1, tbTotalDailyDose_1);
    table.setWidget(13, 1, tbTotalDailyDose_2);
    table.setWidget(14, 1, tbTotalDailyDose_3);

    table.setText(16, 0, "Insulin:");
    table.setWidget(17, 0, tbInsulin_1);
    table.setWidget(18, 0, tbInsulin_2);
    table.setWidget(19, 0, tbInsulin_3);
    table.setWidget(20, 0, tbInsulin_4);

    table.setText(16, 1, "Insulin dose:");
    table.setWidget(17, 1, tbInsulinDose_1);
    table.setWidget(18, 1, tbInsulinDose_2);
    table.setWidget(19, 1, tbInsulinDose_3);
    table.setWidget(20, 1, tbInsulinDose_4);

    table.setText(16, 2, "Insulin regime:");
    table.setWidget(17, 2, tbInsulinRegime_1);
    table.setWidget(18, 2, tbInsulinRegime_2);
    table.setWidget(19, 2, tbInsulinRegime_3);
    table.setWidget(20, 2, tbInsulinRegime_4);

    table.setText(16, 3, "Device:");
    table.setWidget(17, 3, tbDevice_1);
    table.setWidget(18, 3, tbDevice_2);
    table.setWidget(19, 3, tbDevice_3);
    table.setWidget(20, 3, tbDevice_4);

    form.add(table);

    initWidget(form);
  }

  public void save() {
    String postData = Defines.STUDY_NO_KEY + "=" + studyNo + "&";
    postData += Defines.TABLE_NAME_KEY + "=" + PAGE_ID + "&";
    postData += tbHoursOfIVInsulin.getName() + "=" + tbHoursOfIVInsulin.getText() + "&";
    postData += tbOralHypoglycemic_1.getName() + "=" + tbOralHypoglycemic_1.getText() + "&";
    postData += tbOralHypoglycemic_2.getName() + "=" + tbOralHypoglycemic_2.getText() + "&";
    postData += tbOralHypoglycemic_3.getName() + "=" + tbOralHypoglycemic_3.getText() + "&";
    postData += tbTotalDailyDose_1.getName() + "=" + tbTotalDailyDose_1.getText() + "&";
    postData += tbTotalDailyDose_2.getName() + "=" + tbTotalDailyDose_2.getText() + "&";
    postData += tbTotalDailyDose_3.getName() + "=" + tbTotalDailyDose_3.getText() + "&";
    postData += tbInsulin_1.getName() + "=" + tbInsulin_1.getText() + "&";
    postData += tbInsulin_2.getName() + "=" + tbInsulin_2.getText() + "&";
    postData += tbInsulin_3.getName() + "=" + tbInsulin_3.getText() + "&";
    postData += tbInsulin_4.getName() + "=" + tbInsulin_4.getText() + "&";
    postData += tbInsulinDose_1.getName() + "=" + tbInsulinDose_1.getText() + "&";
    postData += tbInsulinDose_2.getName() + "=" + tbInsulinDose_2.getText() + "&";
    postData += tbInsulinDose_3.getName() + "=" + tbInsulinDose_3.getText() + "&";
    postData += tbInsulinDose_4.getName() + "=" + tbInsulinDose_4.getText() + "&";
    postData += tbInsulinRegime_1.getName() + "=" + tbInsulinRegime_1.getText() + "&";
    postData += tbInsulinRegime_2.getName() + "=" + tbInsulinRegime_2.getText() + "&";
    postData += tbInsulinRegime_3.getName() + "=" + tbInsulinRegime_3.getText() + "&";
    postData += tbInsulinRegime_4.getName() + "=" + tbInsulinRegime_4.getText() + "&";
    postData += tbDevice_1.getName() + "=" + tbDevice_1.getText() + "&";
    postData += tbDevice_2.getName() + "=" + tbDevice_2.getText() + "&";
    postData += tbDevice_3.getName() + "=" + tbDevice_3.getText() + "&";
    postData += tbDevice_4.getName() + "=" + tbDevice_4.getText() + "&";
    postData += lbIVInsulinStopped.getName() + "=" + lbIVInsulinStopped.getValue(lbIVInsulinStopped.getSelectedIndex()) + "&";
    postData += lbBicarbonateNormalised.getName() + "=" + lbBicarbonateNormalised.getValue(lbBicarbonateNormalised.getSelectedIndex()) + "&";
    postData += lbUrineKetonesCleared.getName() + "=" + lbUrineKetonesCleared.getValue(lbUrineKetonesCleared.getSelectedIndex()) + "&";
    postData += lbTwoMeals.getName() + "=" + lbTwoMeals.getValue(lbTwoMeals.getSelectedIndex()) + "&";
    postData += lbRestartingLongActingInsulinAnalogue.getName() + "=" + lbRestartingLongActingInsulinAnalogue.getValue(lbRestartingLongActingInsulinAnalogue.getSelectedIndex()) + "&";
    String checkValue = null;
    if (cbPatientOnLongActingInsulinAnalogue.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbPatientOnLongActingInsulinAnalogue.getName() + "=" + checkValue + "&";
    if (cbLongActingInsulinAnalogueContinued.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbLongActingInsulinAnalogueContinued.getName() + "=" + checkValue + "&";

    postData += Utils.DATE_PARAM + "=" + Utils.getDate();

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for TransferToSCInsulin : " + re.getMessage());
    }
  }

  public void loadPreviousData(String studyNo) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, Defines.FORM_HANDLER + "?mode=loaddata&StudyNo=" + studyNo + "&TableName=" + PAGE_ID + "&" +
                                                Utils.DATE_PARAM + "=" + Utils.getDate());
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(null, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to load the data for TransferToSCInsulin : " + re.getMessage());
    }
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for TransferToSCInsulin : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      // Format is WIDGET_NAME-------VALUE@@@@@@@
      String[] namesAndValues = response.getText().split("@@@@@@@");
      for (int c=0; c < namesAndValues.length; c++) {
        String[] parts = namesAndValues[c].split("-------");
        for (int cc=0; cc < parts.length; cc++) {
          if (parts[0].equalsIgnoreCase("HoursOfIVInsulin")) tbHoursOfIVInsulin.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("OralHypoglycemic_1")) tbOralHypoglycemic_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("OralHypoglycemic_2")) tbOralHypoglycemic_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("OralHypoglycemic_3")) tbOralHypoglycemic_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("TotalDailyDose_1")) tbTotalDailyDose_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("TotalDailyDose_2")) tbTotalDailyDose_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("TotalDailyDose_3")) tbTotalDailyDose_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Insulin_1")) tbInsulin_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Insulin_2")) tbInsulin_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Insulin_3")) tbInsulin_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Insulin_4")) tbInsulin_4.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinDose_1")) tbInsulinDose_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinDose_2")) tbInsulinDose_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinDose_3")) tbInsulinDose_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinDose_4")) tbInsulinDose_4.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinRegime_1")) tbInsulinRegime_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinRegime_2")) tbInsulinRegime_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinRegime_3")) tbInsulinRegime_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("InsulinRegime_4")) tbInsulinRegime_4.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Device_1")) tbDevice_1.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Device_2")) tbDevice_2.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Device_3")) tbDevice_3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Device_4")) tbDevice_4.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("IVInsulinStopped")) lbIVInsulinStopped.setSelectedIndex(Utils.getListBoxSelectedIndex(lbIVInsulinStopped, parts[1]));
          if (parts[0].equalsIgnoreCase("BicarbonateNormalised")) lbBicarbonateNormalised.setSelectedIndex(Utils.getListBoxSelectedIndex(lbBicarbonateNormalised, parts[1]));
          if (parts[0].equalsIgnoreCase("UrineKetonesCleared")) lbUrineKetonesCleared.setSelectedIndex(Utils.getListBoxSelectedIndex(lbUrineKetonesCleared, parts[1]));
          if (parts[0].equalsIgnoreCase("TwoMeals")) lbTwoMeals.setSelectedIndex(Utils.getListBoxSelectedIndex(lbTwoMeals, parts[1]));
          if (parts[0].equalsIgnoreCase("RestartingLongActingInsulinAnalogue")) lbRestartingLongActingInsulinAnalogue.setSelectedIndex(Utils.getListBoxSelectedIndex(lbRestartingLongActingInsulinAnalogue, parts[1]));
          if (parts[0].equalsIgnoreCase("PatientOnLongActingInsulinAnalogue")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbPatientOnLongActingInsulinAnalogue.setChecked(true);
            else
              cbPatientOnLongActingInsulinAnalogue.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("LongActingInsulinAnalogueContinued")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbLongActingInsulinAnalogueContinued.setChecked(true);
            else
              cbLongActingInsulinAnalogueContinued.setChecked(false);
          }
        }
      }
    }
  }
}
