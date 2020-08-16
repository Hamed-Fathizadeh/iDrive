package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.AutoTabelle;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.windows.AutoEintragenWindow;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.ContainerEingetrageneAutos;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.util.Roles;

import java.util.List;

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


        Vertriebler vertriebler = (Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER);

        ContainerEingetrageneAutos containerEingetrageneAutos = ContainerEingetrageneAutos.getInstance();
        containerEingetrageneAutos.loadByPersonalnummer(vertriebler.getPersonalnummer());
        List<AutoEintragDTO> autoListe = containerEingetrageneAutos.getListe();

        AutoTabelle<AutoEintragDTO> gAutos = new AutoTabelle<AutoEintragDTO>(autoListe);
        gAutos.setSizeFull();


        mainGrid.addComponent(topPanel,0,1,0,1);

        Label lPatzhalter1= new Label("&nbsp", ContentMode.HTML);
        mainGrid.addComponent(lPatzhalter1,0,2,0,2);

        mainGrid.addComponent(autoEintragenButton,0,3,0,3);

        Label lPatzhalter2= new Label("&nbsp", ContentMode.HTML);
        mainGrid.addComponent(lPatzhalter2,0,4,0,4);

        mainGrid.addComponent(gAutos,0,5,0,5);

        mainGrid.setComponentAlignment(autoEintragenButton,Alignment.BOTTOM_CENTER);
        mainGrid.setComponentAlignment(gAutos,Alignment.BOTTOM_CENTER);
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
