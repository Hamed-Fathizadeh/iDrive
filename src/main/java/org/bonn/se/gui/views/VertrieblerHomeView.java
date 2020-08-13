package org.bonn.se.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.UserSearchControl;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.windows.AutoEintragenWindow;
import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;

public class VertrieblerHomeView extends VerticalLayout implements View {
    public void setUp(){

        GridLayout mainGrid = new GridLayout(1, 7);
        mainGrid.setSizeFull();
        TopPanelUser topPanel = new TopPanelUser();

        Button autoEintragenButton = new Button("Auto eintragen");

        autoEintragenButton.addClickListener(
                event -> {
                    AutoEintragenWindow autoEintragenWindow = new AutoEintragenWindow();
                    UI.getCurrent().addWindow(autoEintragenWindow);

                });






        mainGrid.addComponent(topPanel,0,1,0,1);
        mainGrid.addComponent(autoEintragenButton,0,2,0,2);

        mainGrid.setComponentAlignment(autoEintragenButton,Alignment.BOTTOM_CENTER);
        this.addComponent(mainGrid);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

     /*  if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
           this.setUp();
       } else if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }*/
        this.setUp();

    }


}
