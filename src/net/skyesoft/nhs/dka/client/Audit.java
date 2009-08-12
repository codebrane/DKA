/*
 * Copyright 2006 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

/**
 * Introduction page.
 */
public class Audit extends Sink {
  private static final String PAGE_ID = "generalinfo";
  private static final String NAME_IN_LIST = "General";
  private static final String DESCRIPTION = "General information";
  
  final FormPanel form = new FormPanel();
  final TextBox tbStudyNo = new TextBox();
  final TextBox tbAge = new TextBox();
  final TextBox tbDaysInPatient = new TextBox();
  final TextBox tbHCO3 = new TextBox();
  final TextBox tbPH = new TextBox();
  final TextBox tbPHRangeUpper = new TextBox();
  final TextBox tbPHRangeLower = new TextBox();
  final ListBox lbSex = new ListBox();
  final ListBox lbTypeOfDiabetes = new ListBox();
  final ListBox lbSourceOfReferral = new ListBox();
  final ListBox lbSymptoms = new ListBox();
  final ListBox lbBM15 = new ListBox();
  final ListBox lbKetones = new ListBox();
  final ListBox lbDiagnosisConfirmed = new ListBox();
  final TextBox tbBloodGlucoseLevelOnArrival = new TextBox();

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new Audit();
      }
    };
  }

  public Audit() {
    form.setAction(Defines.FORM_HANDLER);
    form.setMethod(FormPanel.METHOD_POST);

    // Text boxes ///////////////////////////////////////////////////////////////////
    tbStudyNo.setName("StudyNo");
    tbAge.setName("Age");
    tbDaysInPatient.setName("DaysInPatient");
    tbHCO3.setName("HCO3");
    tbPH.setName("PH");
    tbPHRangeUpper.setName("PHRangeUpper");
    tbPHRangeLower.setName("PHRangeLower");
    tbBloodGlucoseLevelOnArrival.setName("BloodGlucoseLevelOnArrival");
    // //////////////////////////////////////////////////////////////////////////////

    // List boxes ///////////////////////////////////////////////////////////////////
    lbSex.setName("Sex");
    lbSex.addItem("Male", "male");
    lbSex.addItem("Female", "female");

    lbTypeOfDiabetes.setName("TypeOfDiabetes");
    lbTypeOfDiabetes.addItem("Type 1", "type1");
    lbTypeOfDiabetes.addItem("Type 2", "type2");
    lbTypeOfDiabetes.addItem("Secondary", "secondary");
    lbTypeOfDiabetes.addItem("Gestational", "gestational");
    lbTypeOfDiabetes.addItem("Not known", "notknown");

    lbSourceOfReferral.setName("SourceOfReferral");
    lbSourceOfReferral.addItem("GP", "gp");
    lbSourceOfReferral.addItem("NHS 24", "nhs24");
    lbSourceOfReferral.addItem("A&E", "aande");
//    lbSourceOfReferral.addItem("Surgery", "surgery");
//    lbSourceOfReferral.addItem("Orthopaedics", "orthopaedics");

    lbSymptoms.setName("Symptoms");
    lbSymptoms.addItem("Yes", "yes");
    lbSymptoms.addItem("No", "no");

    lbBM15.setName("BMGreaterThan15");
    lbBM15.addItem("Yes", "yes");
    lbBM15.addItem("No", "no");

    lbKetones.setName("Ketones");
    lbKetones.addItem("None", "none");
    lbKetones.addItem("Trace", "trace");
    lbKetones.addItem("Mild +", "mild");
    lbKetones.addItem("Moderate ++", "moderate");
    lbKetones.addItem("Severe +++", "severe");
    lbKetones.addItem("Large ++++", "large");

    lbDiagnosisConfirmed.setName("DiagnosisConfirmed");
    lbDiagnosisConfirmed.addItem("Yes", "yes");
    lbDiagnosisConfirmed.addItem("No", "no");

    lbStudyNos.setName("StudyNos");
    // //////////////////////////////////////////////////////////////////////////////

    form.addFormHandler(new FormHandler() {
        public void onSubmitComplete(FormSubmitCompleteEvent event) {
        }

        public void onSubmit(FormSubmitEvent event) {
        }
    });

    final FlexTable table = new FlexTable();

    table.setText(0, 0, "Study No:");
    table.setWidget(0, 1, tbStudyNo);

    table.setText(1, 0, "Sex:");
    table.setWidget(1, 1, lbSex);

    table.setText(2, 0, "Age in yrs:");
    table.setWidget(2, 1, tbAge);

    table.setText(3, 0, "Type of diabetes:");
    table.setWidget(3, 1, lbTypeOfDiabetes);
    table.setText(3, 2, "Source of referral:");
    table.setWidget(3, 3, lbSourceOfReferral);
    table.setText(3, 4, "Days inpatient:");
    table.setWidget(3, 5, tbDaysInPatient);

    table.setText(4, 0, "Symptoms:");
    table.setWidget(4, 1, lbSymptoms);
    table.setText(4, 2, "Ketones:");
    table.setWidget(4, 3, lbKetones);

    table.setText(5, 0, "BM > 15:");
    table.setWidget(5, 1, lbBM15);
    table.setText(5, 2, "HCO3:");
    table.setWidget(5, 3, tbHCO3);

    table.setText(6, 0, "ph:");
    table.setWidget(6, 1, tbPH);
    table.setText(6, 2, "ph range upper:");
    table.setWidget(6, 3, tbPHRangeUpper);
    table.setText(6, 4, "ph range lower:");
    table.setWidget(6, 5, tbPHRangeLower);

    table.setText(7, 0, "Was diagnosis confirmed?:");
    table.setWidget(7, 1, lbDiagnosisConfirmed);

    table.setText(8, 0, "Blood glucose level on arrival:");
    table.setWidget(8, 1, tbBloodGlucoseLevelOnArrival);

    form.add(table);

    initWidget(form);
  }

  public String getStudyNo() {
    return tbStudyNo.getText();
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
      Window.alert("Failed to load the data for Audit : " + re.getMessage());
    }
  }

  public void save() {
    studyNo = tbStudyNo.getText();
    
    String postData = tbStudyNo.getName() + "=" + tbStudyNo.getText() + "&";
    postData += Defines.TABLE_NAME_KEY + "=" + PAGE_ID + "&";
    postData += tbStudyNo.getName() + "=" + tbStudyNo.getText() + "&";
    postData += tbAge.getName() + "=" + tbAge.getText() + "&";
    postData += tbDaysInPatient.getName() + "=" + tbDaysInPatient.getText() + "&";
    postData += tbHCO3.getName() + "=" + tbHCO3.getText() + "&";
    postData += tbPH.getName() + "=" + tbPH.getText() + "&";
    postData += tbPHRangeUpper.getName() + "=" + tbPHRangeUpper.getText() + "&";
    postData += tbPHRangeLower.getName() + "=" + tbPHRangeLower.getText() + "&";
    postData += lbSex.getName() + "=" + lbSex.getValue(lbSex.getSelectedIndex()) + "&";
    postData += lbTypeOfDiabetes.getName() + "=" + lbTypeOfDiabetes.getValue(lbTypeOfDiabetes.getSelectedIndex()) + "&";
    postData += lbSourceOfReferral.getName() + "=" + lbSourceOfReferral.getValue(lbSourceOfReferral.getSelectedIndex()) + "&";
    postData += lbSymptoms.getName() + "=" + lbSymptoms.getValue(lbSymptoms.getSelectedIndex()) + "&";
    postData += lbBM15.getName() + "=" + lbBM15.getValue(lbBM15.getSelectedIndex()) + "&";
    postData += lbKetones.getName() + "=" + lbKetones.getValue(lbKetones.getSelectedIndex()) + "&";
    postData += lbDiagnosisConfirmed.getName() + "=" + lbDiagnosisConfirmed.getValue(lbDiagnosisConfirmed.getSelectedIndex()) + "&";
    postData += tbBloodGlucoseLevelOnArrival.getName() + "=" + tbBloodGlucoseLevelOnArrival.getText() + "&";
    
    postData += Utils.DATE_PARAM + "=" + Utils.getDate();

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for Audit : " + re.getMessage());
    }
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for Audit : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      // Format is WIDGET_NAME-------VALUE@@@@@@@
      String[] namesAndValues = response.getText().split("@@@@@@@");
      for (int c=0; c < namesAndValues.length; c++) {
        String[] parts = namesAndValues[c].split("-------");
        for (int cc=0; cc < parts.length; cc++) {
          if (parts[0].equalsIgnoreCase("StudyNo")) tbStudyNo.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Age")) tbAge.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("DaysInPatient")) tbDaysInPatient.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("HCO3")) tbHCO3.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("PH")) tbPH.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("PHRangeUpper")) tbPHRangeUpper.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("PHRangeLower")) tbPHRangeLower.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("Sex")) lbSex.setSelectedIndex(Utils.getListBoxSelectedIndex(lbSex, parts[1]));
          if (parts[0].equalsIgnoreCase("TypeOfDiabetes")) lbTypeOfDiabetes.setSelectedIndex(Utils.getListBoxSelectedIndex(lbTypeOfDiabetes, parts[1]));
          if (parts[0].equalsIgnoreCase("SourceOfReferral")) lbSourceOfReferral.setSelectedIndex(Utils.getListBoxSelectedIndex(lbSourceOfReferral, parts[1]));
          if (parts[0].equalsIgnoreCase("Symptoms")) lbSymptoms.setSelectedIndex(Utils.getListBoxSelectedIndex(lbSymptoms, parts[1]));
          if (parts[0].equalsIgnoreCase("BMGreaterThan15")) lbBM15.setSelectedIndex(Utils.getListBoxSelectedIndex(lbBM15, parts[1]));
          if (parts[0].equalsIgnoreCase("Ketones")) lbKetones.setSelectedIndex(Utils.getListBoxSelectedIndex(lbKetones, parts[1]));
          if (parts[0].equalsIgnoreCase("DiagnosisConfirmed")) lbDiagnosisConfirmed.setSelectedIndex(Utils.getListBoxSelectedIndex(lbDiagnosisConfirmed, parts[1]));
          if (parts[0].equalsIgnoreCase("BloodGlucoseLevelOnArrival")) tbBloodGlucoseLevelOnArrival.setText(parts[1]);
        }
      }
    }
  }
}
