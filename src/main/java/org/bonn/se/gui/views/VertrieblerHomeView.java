package org.bonn.se.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.SQLException;

public class VertrieblerHomeView extends VerticalLayout implements View {
    public void setUp(){

        GridLayout mainGrid = new GridLayout(1, 7);
        mainGrid.setSizeFull();
        TopPanelUser topPanel = new TopPanelUser();

        mainGrid.addComponent(topPanel,0,1,0,1);


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
