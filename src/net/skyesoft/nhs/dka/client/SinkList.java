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

import net.skyesoft.nhs.dka.client.Sink.SinkInfo;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.HTTPRequest;
import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.Window;

import java.util.ArrayList;

/**
 * The left panel that contains all of the sinks, along with a short description
 * of each.
 */
public class SinkList extends Composite {

  private VerticalPanel list = new VerticalPanel();
  private ArrayList sinks = new ArrayList();
  private int selectedSink = -1;
  private int nonSinkObjectsCountAtStartOfList = 0;

  FlexTable logoTable = new FlexTable();
  FlexTable currentStudyNoTable = new FlexTable();
  FlexTable loadDataTable = new FlexTable();
  FlexTable saveTable = new FlexTable();
  ListBox lbStudyNos = new ListBox();
  final Button loadButton = new Button("Load");
  final Button saveButton = new Button("Save");

  public SinkList() {
    initWidget(list);
    setStyleName("ks-List");
  }

  public void addCurrentStudyNoDisplay() {
    currentStudyNoTable.setVisible(false);
    currentStudyNoTable.setBorderWidth(2);
    currentStudyNoTable.setStyleName("currentStudyNo");
    currentStudyNoTable.setText(0, 0, "Current Study No:");
    currentStudyNoTable.setText(1, 0, "No study loaded");
    list.add(currentStudyNoTable);
  }

  public void addPreviousDataLoader() {
    loadDataTable.setBorderWidth(2);
    loadDataTable.setStyleName("previousDataLoader");
    loadDataTable.setText(0, 0, "Load previous study:");
    loadDataTable.setWidget(2, 0, lbStudyNos);
    loadDataTable.setWidget(4, 0, loadButton);

    list.add(loadDataTable);

    HTTPRequest.asyncGet(Defines.FORM_HANDLER + "?mode=getstudynos&TableName=generalinfo",
                         new PreviousDataLoader());

    lbStudyNos.setTitle("Load previous study");
    
    loadButton.addClickListener(new PreviousDataDisplayer());
  }

  class PreviousDataLoader implements ResponseTextHandler {
    public void onCompletion(String responseText) {
      /* Format is:
       * studyno,studyno, ...
       */
      lbStudyNos.clear();

      String[] parts = responseText.split(",");
      for (int c=0; c < parts.length; c++) {
        lbStudyNos.addItem(parts[c], parts[c]);
      }
    }
  }

  public void addSaveButton() {
    saveTable.setBorderWidth(2);
    saveTable.setStyleName("saveTable");
    saveTable.setText(0, 0, "Save all study data");
    saveTable.setWidget(4, 0, saveButton);

    saveButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        find("General").getInstance().save();
        find("Treatment and investigation").getInstance().save();
        find("Transfer to s/c insulin").getInstance().save();
        find("Education and follow up").getInstance().save();
        find("IV Fluids").getInstance().save();
        find("Routine bloods").getInstance().save();
        
        currentStudyNoTable.setText(1, 0, find("General").getInstance().getStudyNo());
        currentStudyNoTable.setVisible(true);

        Window.alert("All study data for " + find("General").getInstance().getStudyNo() + " saved to the database");
      }
    });

    list.add(saveTable);
  }

  public void addLogo() {
    Image logo = new Image("images/nhs_logo.jpg");
    logoTable.setWidget(0, 0, logo);
    list.add(logo);
    nonSinkObjectsCountAtStartOfList++;
  }

  public void addSink(final SinkInfo info) {
    String name = info.getName();
    Hyperlink link = new Hyperlink(name, name);
    link.setStyleName("ks-SinkItem");

    list.add(link);
    sinks.add(info);
  }

  public SinkInfo find(String sinkName) {
    for (int i = 0; i < sinks.size(); ++i) {
      SinkInfo info = (SinkInfo) sinks.get(i);
      if (info.getName().equals(sinkName))
        return info;
    }

    return null;
  }

  public void setSinkSelection(String name) {
    if (selectedSink != -1) {
      // Need to increment by how many non Sink objects are in the list before the the first Sink
      list.getWidget(selectedSink + nonSinkObjectsCountAtStartOfList).removeStyleName("ks-SinkItem-selected");
    }

    for (int i = 0; i < sinks.size(); ++i) {
      SinkInfo info = (SinkInfo) sinks.get(i);
      if (info.getName().equals(name)) {
        selectedSink = i;
        // Need to increment by how many non Sink objects are in the list before the the first Sink
        list.getWidget(selectedSink + nonSinkObjectsCountAtStartOfList).addStyleName("ks-SinkItem-selected");
        return;
      }
    }
  }

  class PreviousDataDisplayer implements ClickListener {
    public void onClick(Widget widget) {
      find("General").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));
      find("Treatment and investigation").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));
      find("Transfer to s/c insulin").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));
      find("Education and follow up").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));
      find("IV Fluids").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));
      find("Routine bloods").getInstance().loadPreviousData(lbStudyNos.getValue(lbStudyNos.getSelectedIndex()));

      currentStudyNoTable.setText(1, 0, lbStudyNos.getItemText(lbStudyNos.getSelectedIndex()));
      currentStudyNoTable.setVisible(true);

      Window.alert("Data for " + lbStudyNos.getItemText(lbStudyNos.getSelectedIndex()) + " loaded from the database");
    }
  }
}
