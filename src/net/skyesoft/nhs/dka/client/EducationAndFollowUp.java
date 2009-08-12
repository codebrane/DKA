/* CVS Header
   $Id$
   $Log$
*/

package net.skyesoft.nhs.dka.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.http.client.*;

public class EducationAndFollowUp extends Sink {
  private static final String PAGE_ID = "educationandfollowup";
  private static final String NAME_IN_LIST = "Education and follow up";
  private static final String DESCRIPTION = "Education and follow up";

  TextBox tbOther = new TextBox();
  TextBox tbAttendedDiabetesCourse = new TextBox();
  CheckBox cbNoEducationProvided = new CheckBox("No eduation provided");
  CheckBox cbCommercialLeaflets = new CheckBox("Commercial leaflets");
  CheckBox cbVerbalUpdate = new CheckBox("Verbal update");
  CheckBox cbKetocard = new CheckBox("Ketocard");
  CheckBox cbDiabetesWebsite = new CheckBox("Diabetes website");
  CheckBox cbAfterPreviousDKA = new CheckBox("After previous DKA");
  CheckBox cbInformationLeaflet = new CheckBox("Information leaflet");
  CheckBox cbOnlineDiabetesResources = new CheckBox("Online diabetes resources");
  CheckBox cbAtTimeOfInitialDiagnosis = new CheckBox("At time of initial diagnosis");
  CheckBox cbPatientHasAppropriateFollowUpInPlace = new CheckBox("Patient has appropriate follow up in place");

  final FormPanel form = new FormPanel();

  public static SinkInfo init() {
    return new SinkInfo(NAME_IN_LIST, DESCRIPTION) {
      public Sink createInstance() {
        return new EducationAndFollowUp();
      }
    };
  }

  public EducationAndFollowUp() {
    form.setAction(Defines.FORM_HANDLER);
    form.setMethod(FormPanel.METHOD_POST);

    form.addFormHandler(new FormHandler() {
        public void onSubmitComplete(FormSubmitCompleteEvent event) {
        }

        public void onSubmit(FormSubmitEvent event) {
        }
    });

    // Text boxes ///////////////////////////////////////////////////////////////////
    tbOther.setName("Other");
    tbAttendedDiabetesCourse.setName("AttendedDiabetesCourse");
    // //////////////////////////////////////////////////////////////////////////////

    // Check boxes ///////////////////////////////////////////////////////////////////
    cbNoEducationProvided.setName("NoEducationProvided");
    cbCommercialLeaflets.setName("CommercialLeaflets");
    cbVerbalUpdate.setName("VerbalUpdate");
    cbKetocard.setName("Ketocard");
    cbDiabetesWebsite.setName("DiabetesWebsite");
    cbAfterPreviousDKA.setName("AfterPreviousDKA");
    cbInformationLeaflet.setName("InformationLeaflet");
    cbOnlineDiabetesResources.setName("OnlineDiabetesResources");
    cbAtTimeOfInitialDiagnosis.setName("AtTimeOfInitialDiagnosis");
    cbPatientHasAppropriateFollowUpInPlace.setName("PatientHasAppropriateFollowUpInPlace");
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

    table.setText(0, 0, "Diabetes education at discharge");

    table.setWidget(5, 0, cbNoEducationProvided);
    table.setWidget(6, 0, cbCommercialLeaflets);
    table.setWidget(7, 0, cbVerbalUpdate);
    table.setWidget(8, 0, cbKetocard);
    table.setWidget(9, 0, cbDiabetesWebsite);
    table.setText(10, 0, "Other:");
    table.setWidget(11, 0, tbOther);

    table.setText(0, 5, "Previous diabetes education");

    table.setWidget(5, 5, cbAfterPreviousDKA);
    table.setWidget(6, 5, cbInformationLeaflet);
    table.setWidget(7, 5, cbOnlineDiabetesResources);
    table.setWidget(8, 5, cbAtTimeOfInitialDiagnosis);
    table.setText(9, 5, "If has attended diabetes course, state name");
    table.setWidget(9, 6, tbAttendedDiabetesCourse);
    table.setWidget(10, 5, cbPatientHasAppropriateFollowUpInPlace);

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
      Window.alert("Failed to load the data for EducationAndFollowUp : " + re.getMessage());
    }
  }

  public void save() {
    String checkValue = null;
    
    String postData = Defines.STUDY_NO_KEY + "=" + studyNo + "&";
    postData += Defines.TABLE_NAME_KEY + "=" + PAGE_ID + "&";
    postData += tbOther.getName() + "=" + tbOther.getText() + "&";
    postData += tbAttendedDiabetesCourse.getName() + "=" + tbAttendedDiabetesCourse.getText() + "&";

    if (cbNoEducationProvided.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbNoEducationProvided.getName() + "=" + checkValue + "&";

    if (cbCommercialLeaflets.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbCommercialLeaflets.getName() + "=" + checkValue + "&";
    if (cbVerbalUpdate.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbVerbalUpdate.getName() + "=" + checkValue + "&";
    if (cbKetocard.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbKetocard.getName() + "=" + checkValue + "&";
    if (cbDiabetesWebsite.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbDiabetesWebsite.getName() + "=" + checkValue + "&";
    if (cbAfterPreviousDKA.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbAfterPreviousDKA.getName() + "=" + checkValue + "&";
    if (cbInformationLeaflet.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbInformationLeaflet.getName() + "=" + checkValue + "&";
    if (cbOnlineDiabetesResources.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbOnlineDiabetesResources.getName() + "=" + checkValue + "&";
    if (cbAtTimeOfInitialDiagnosis.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbAtTimeOfInitialDiagnosis.getName() + "=" + checkValue + "&";
    if (cbPatientHasAppropriateFollowUpInPlace.isChecked()) checkValue = "yes";
    else checkValue = "no";
    postData += cbPatientHasAppropriateFollowUpInPlace.getName() + "=" + checkValue + "&";

    postData += Utils.DATE_PARAM + "=" + Utils.getDate();

    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, Defines.FORM_HANDLER);
    builder.setUser(Defines.USERNAME);
    builder.setPassword(Defines.PASSWORD);
    builder.setTimeoutMillis(Defines.TIMEOUT);

    try {
      builder.sendRequest(postData, new PreviousDataLoader());
    }
    catch(RequestException re) {
      Window.alert("Failed to save the data for EducationAndFollowUp : " + re.getMessage());
    }
  }

  class PreviousDataLoader implements RequestCallback {
    public void onError(Request request, Throwable exception) {
      Window.alert("Failed to load the data for EducationAndFollowUp : " + exception.getMessage());
    }

    public void onResponseReceived(Request request, Response response) {
      // Format is WIDGET_NAME-------VALUE@@@@@@@
      String[] namesAndValues = response.getText().split("@@@@@@@");
      for (int c=0; c < namesAndValues.length; c++) {
        String[] parts = namesAndValues[c].split("-------");
        for (int cc=0; cc < parts.length; cc++) {
          if (parts[0].equalsIgnoreCase("Other")) tbOther.setText(parts[1]);
          if (parts[0].equalsIgnoreCase("AttendedDiabetesCourse")) tbAttendedDiabetesCourse.setText(parts[1]);

          if (parts[0].equalsIgnoreCase("NoEducationProvided")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbNoEducationProvided.setChecked(true);
            else
              cbNoEducationProvided.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("CommercialLeaflets")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbCommercialLeaflets.setChecked(true);
            else
              cbCommercialLeaflets.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("VerbalUpdate")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbVerbalUpdate.setChecked(true);
            else
              cbVerbalUpdate.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("Ketocard")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbKetocard.setChecked(true);
            else
              cbKetocard.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("DiabetesWebsite")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbDiabetesWebsite.setChecked(true);
            else
              cbDiabetesWebsite.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("AfterPreviousDKA")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbAfterPreviousDKA.setChecked(true);
            else
              cbAfterPreviousDKA.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("InformationLeaflet")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbInformationLeaflet.setChecked(true);
            else
              cbInformationLeaflet.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("OnlineDiabetesResources")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbOnlineDiabetesResources.setChecked(true);
            else
              cbOnlineDiabetesResources.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("AtTimeOfInitialDiagnosis")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbAtTimeOfInitialDiagnosis.setChecked(true);
            else
              cbAtTimeOfInitialDiagnosis.setChecked(false);
          }
          if (parts[0].equalsIgnoreCase("PatientHasAppropriateFollowUpInPlace")) {
            if (parts[1].equalsIgnoreCase("yes"))
              cbPatientHasAppropriateFollowUpInPlace.setChecked(true);
            else
              cbPatientHasAppropriateFollowUpInPlace.setChecked(false);
          }
        }
      }
    }
  }
}
