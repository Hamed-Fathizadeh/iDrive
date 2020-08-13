package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;

public class VertrieblerHomeView extends VerticalLayout implements View {
    public void setUp() throws DatabaseException, SQLException {

        GridLayout mainGrid = new GridLayout(1, 7);
        mainGrid.setSizeFull();
        TopPanelUser topPanel = new TopPanelUser();

        String ls3 = "<p class=MsoNormal><b><span style='font-size:20.0pt;line-height:107%;\n" +
                "font-family:\"Arial\",sans-serif;mso-ascii-theme-font:minor-bidi;mso-hansi-theme-font:\n" +
                "minor-bidi;mso-bidi-theme-font:minor-bidi;color:#2F5597;mso-themecolor:accent1;\n" +
                "mso-themeshade:191;mso-style-textfill-fill-color:#2F5597;mso-style-textfill-fill-themecolor:\n" +
                "accent1;mso-style-textfill-fill-alpha:100.0%;mso-style-textfill-fill-colortransforms:\n" +
                "lumm=75000'>Finde JETZT deinen Traumjob!<o:p></o:p></span></b></p>";

        Label lSpruch = new Label(ls3, ContentMode.HTML);

        mainGrid.addComponent(topPanel,0,1,0,1);
        mainGrid.addComponent(lSpruch,0,1,0,1);
    }



}
