/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

public class TreatmentAndInvestigation extends Sink {
  private static final String PAGE_ID = "treatmentandinvestigation";
  private static final String NAME_IN_LIST = "Treatment and investigation";
  private static final String DESCRIPTION = "Treatment and investigation";

  final FlexTable table = new FlexTable();

  final FormPanel form = new FormPanel();
  TextBox tbCauseDetails = new TextBox();
  ListBox lbMSSU = new ListBox();
  ListBox lbFBC = new ListBox();
  ListBox lbECG = new ListBox();
  ListBox lbCauseIdentified = new ListBox();
  ListBox lbCXR = new ListBox();
  ListBox lbViralTitres = new ListBox();
  ListBox lbAntibiotics = new ListBox();
  ListBox lbFluidBalanceChart = new ListBox();
  ListBox lbNGTube = new ListBox();
  ListBox lbCentralLine = new ListBox();
  ListBox lbIntensiveCare = new ListBox();
  ListBox lbCatheter = new ListBox();
  ListBox lbFollowUp = new ListBox();
  TextBox tbBloodCultures = new TextBox();
  TextBox tbTimeIntervalFromAdmissionToFluids = new TextBox();
  TextBox tbTimeIntervalFromAdmissionToInsulin = new TextBox();

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new TreatmentAndInvestigation();
      }
    };
  }

  public TreatmentAndInvestigation() {
    form.setAction(Defines.FORM_HANDLER);
    form.setMethod(FormPanel.METHOD_POST);

    form.addFormHandler(new FormHandler() {
        public void onSubmitComplete(FormSubmitCompleteEvent event) {
        }

        public void onSubmit(FormSubmitEvent event) {
          Hidden hiddentStudyNo = (Hidden)table.getWidget(101, 101);
          hiddentStudyNo.setValue(studyNo);
          table.setWidget(101, 101, hiddentStudyNo);
        }
    });

    // Text boxes ///////////////////////////////////////////////////////////////////
    tbCauseDetails.setName("CauseDetails");
    tbBloodCultures.setName("BloodCultures");
    tbTimeIntervalFromAdmissionToFluids.setName("TimeIntervalFromAdmissionToFluids");
    tbTimeIntervalFromAdmissionToInsulin.setName("TimeIntervalFromAdmissionToInsulin");
    // //////////////////////////////////////////////////////////////////////////////

    // List boxes ///////////////////////////////////////////////////////////////////
    lbMSSU.setName("MSSU");
    lbMSSU.addItem("Not done", "not done");
    lbMSSU.addItem("Abnormal", "abnormal");
    lbMSSU.addItem("Normal", "normal");

    lbFBC.setName("FBC");
    lbFBC.addItem("Not done", "not done");
    lbFBC.addItem("Abnormal", "abnormal");
    lbFBC.addItem("Normal", "normal");

    lbECG.setName("ECG");
    lbECG.addItem("Not done", "not done");
    lbECG.addItem("Abnormal", "abnormal");
    lbECG.addItem("Normal", "normal");

    lbCauseIdentified.setName("CauseIdentified");
    lbCauseIdentified.addItem("Yes", "yes");
    lbCauseIdentified.addItem("No", "no");

    lbCXR.setName("CXR");
    lbCXR.addItem("Not done", "not done");
    lbCXR.addItem("Abnormal", "abnormal");
    lbCXR.addItem("Normal", "normal");

    lbViralTitres.setName("ViralTitres");
    lbViralTitres.addItem("Not done", "not done");
    lbViralTitres.addItem("Abnormal", "abnormal");
    lbViralTitres.addItem("Normal", "normal");

    lbAntibiotics.setName("Antibiotics");
    lbAntibiotics.addItem("Yes", "yes");
    lbAntibiotics.addItem("No", "no");

    lbFluidBalanceChart.setName("FluidBalanceChart");
    lbFluidBalanceChart.addItem("Yes", "yes");
    lbFluidBalanceChart.addItem("No", "no");

    lbNGTube.setName("NGTube");
    lbNGTube.addItem("Yes", "yes");
    lbNGTube.addItem("No", "no");

    lbCentralLine.setName("CentralLine");
    lbCentralLine.addItem("Yes", "yes");
    lbCentralLine.addItem("No", "no");

    lbIntensiveCare.setName("IntensiveCare");
    lbIntensiveCare.addItem("Yes", "yes");
    lbIntensiveCare.addItem("No", "no");

    lbCatheter.setName("Catheter");
    lbCatheter.addItem("Yes", "yes");
    lbCatheter.addItem("No", "no");

    lbFollowUp.setName("FollowUp");
    lbFollowUp.addItem("Yes", "yes");
    lbFollowUp.addItem("No", "no");
    // //////////////////////////////////////////////////////////////////////////////

    Hidden hiddentStudyNo = new Hidden();
    hiddentStudyNo.setName("StudyNo");
    hiddentStudyNo.setValue(studyNo);
    table.setWidget(101, 101, hiddentStudyNo);

    table.setText(0, 0, "MSSU:");
    table.setWidget(0, 1, lbMSSU);

    table.setText(1, 0, "FBC:");
    table.setWidget(1, 1, lbFBC);

    table.setText(2, 0, "ECG:");
    table.setWidget(2, 1, lbECG);

    table.setText(3, 0, "Cause identified:");
    table.setWidget(3, 1, lbCauseIdentified);

    table.setText(4, 0, "Cause details:");
    table.setWidget(4, 1, tbCauseDetails);

    table.setText(5, 0, "CXR:");
    table.setWidget(5, 1, lbCXR);

    table.setText(6, 0, "Viral titres:");
    table.setWidget(6, 1, lbViralTitres);

    table.setText(7, 0, "Antibiotics:");
    table.setWidget(7, 1, lbAntibiotics);

    table.setText(8, 0, "Fluid balance chart:");
    table.setWidget(8, 1, lbFluidBalanceChart);

    table.setText(9, 0, "NG tube:");
    table.setWidget(9, 1, lbNGTube);

    table.setText(10, 0, "Central line:");
    table.setWidget(10, 1, lbCentralLine);

    table.setText(11, 0, "Intensive care:");
    table.setWidget(11, 1, lbIntensiveCare);

    table.setText(12, 0, "Catheter:");
    table.setWidget(12, 1, lbCatheter);

    table.setText(13, 0, "Was appropriate follow up arranged?");
    table.setWidget(13, 1, lbFollowUp);

    table.setText(14, 0, "Blood cultures");
    table.setWidget(14, 1, tbBloodCultures);

    table.setText(15, 0, "Time interval from admission to fluids");
    table.setWidget(15, 1, tbTimeIntervalFromAdmissionToFluids);

    table.setText(16, 0, "Time interval from admission to insulin");
    table.setWidget(16, 1, tbTimeIntervalFromAdmissionToInsulin);

    form.add(table);

    initWidget(form);
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
      Window.alert("Failed to load the data for TreatmentAndInvestigation : " + re.getMessage());
    }
  }

  public void save() {
    String postData = Defines.STUDY_NO_KEY + "=" + studyNo + "&";
    postData += Defines.TABLE_NAME_KEY + "=" + PAGE_ID + "&";
    postData += tbCauseDetails.getName() + "=" + tbCauseDetails.getText() + "&";
    postData += lbMSSU.getName() + "=" + lbMSSU.getValue(lbMSSU.getSelectedIndex()) + "&";
    postData += lbFBC.getName() + "=" + lbFBC.getValue(lbFBC.getSelectedIndex()) + "&";
    postData += lbECG.getName() + "=" + lbECG.getValue(lbECG.getSelectedIndex()) + "&";
    postData += lbCauseIdentified.getName() + "=" + lbCauseIdentified.getValue(lbCauseIdentified.getSelectedIndex()) + "&";
    postData += lbCXR.getName() + "=" + lbCXR.getValue(lbCXR.getSelectedIndex()) + "&";
    postData += lbViralTitres.getName() + "=" + lbViralTitres.getValue(lbViralTitres.getSelectedIndex()) + "&";
    postData += lbAntibiotics.getName() + "=" + lbAntibiotics.getValue(lbAntibiotics.getSelectedIndex()) + "&";
    postData += lbFluidBalanceChart.getName() + "=" + lbFluidBalanceChart.getValue(lbFluidBalanceChart.getSelectedIndex()) + "&";
    postData += lbNGTube.getName() + "=" + lbNGTube.getValue(lbNGTube.getSelectedIndex()) + "&";
    postData += lbCentralLine.getName() + "=" + lbCentralLine.getValue(lbCentralLine.getSelectedIndex()) + "&";
    postData += lbIntensiveCare.getName() + "=" + lbIntensiveCare.getValue(lbIntensiveCare.getSelectedIndex()) + "&";
    postData += lbCatheter.getName() + "=" + lbCatheter.getValue(lbCatheter.getSelectedIndex()) + "&";
    postData += lbFollowUp.getName() + "=" + lbFollowUp.getValue(lbFollowUp.getSelectedIndex())  + "&";
    postData += tbBloodCultures.getName() + "=" + tbBloodCultures.getText() + "&";
    postData += tbTimeIntervalFromAdmissionToFluids.getName() + "=" + tbTimeIntervalFromAdmissionToFluids.getText() + "&";
    postData += tbTimeIntervalFromAdmissionToInsulin.getName() + "=" + tbTimeIntervalFromAdmissionToInsulin.getText() + "&";
    
    postData += Utils.DATE_PARAM + "=" + Utils.getDate();

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for TreatmentAndInvestigation : " + re.getMessage());
    }
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for TreatmentAndInvestigation : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      // Format is WIDGET_NAME-------VALUE@@@@@@@
      String[] namesAndValues = response.getText().split("@@@@@@@");
      for (int c=0; c < namesAndValues.length; c++) {
        String[] parts = namesAndValues[c].split("-------");
        for (int cc=0; cc < parts.length; cc++) {
          if (parts[0].equalsIgnoreCase("CauseDetails")) tbCauseDetails.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("MSSU")) lbMSSU.setSelectedIndex(Utils.getListBoxSelectedIndex(lbMSSU, parts[1]));
          if (parts[0].equalsIgnoreCase("FBC")) lbFBC.setSelectedIndex(Utils.getListBoxSelectedIndex(lbFBC, parts[1]));
          if (parts[0].equalsIgnoreCase("ECG")) lbECG.setSelectedIndex(Utils.getListBoxSelectedIndex(lbECG, parts[1]));
          if (parts[0].equalsIgnoreCase("CauseIdentified")) lbCauseIdentified.setSelectedIndex(Utils.getListBoxSelectedIndex(lbCauseIdentified, parts[1]));
          if (parts[0].equalsIgnoreCase("CXR")) lbCXR.setSelectedIndex(Utils.getListBoxSelectedIndex(lbCXR, parts[1]));
          if (parts[0].equalsIgnoreCase("ViralTitres")) lbViralTitres.setSelectedIndex(Utils.getListBoxSelectedIndex(lbViralTitres, parts[1]));
          if (parts[0].equalsIgnoreCase("Antibiotics")) lbAntibiotics.setSelectedIndex(Utils.getListBoxSelectedIndex(lbAntibiotics, parts[1]));
          if (parts[0].equalsIgnoreCase("FluidBalanceChart")) lbFluidBalanceChart.setSelectedIndex(Utils.getListBoxSelectedIndex(lbFluidBalanceChart, parts[1]));
          if (parts[0].equalsIgnoreCase("NGTube")) lbNGTube.setSelectedIndex(Utils.getListBoxSelectedIndex(lbNGTube, parts[1]));
          if (parts[0].equalsIgnoreCase("CentralLine")) lbCentralLine.setSelectedIndex(Utils.getListBoxSelectedIndex(lbCentralLine, parts[1]));
          if (parts[0].equalsIgnoreCase("IntensiveCare")) lbIntensiveCare.setSelectedIndex(Utils.getListBoxSelectedIndex(lbIntensiveCare, parts[1]));
          if (parts[0].equalsIgnoreCase("Catheter")) lbCatheter.setSelectedIndex(Utils.getListBoxSelectedIndex(lbCatheter, parts[1]));
          if (parts[0].equalsIgnoreCase("FollowUp")) lbFollowUp.setSelectedIndex(Utils.getListBoxSelectedIndex(lbFollowUp, parts[1]));
          if (parts[0].equalsIgnoreCase("BloodCultures")) tbBloodCultures.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("TimeIntervalFromAdmissionToFluids")) tbTimeIntervalFromAdmissionToFluids.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("TimeIntervalFromAdmissionToInsulin")) tbTimeIntervalFromAdmissionToInsulin.setText(parts[1]);
        }
      }
    }
  }
}
