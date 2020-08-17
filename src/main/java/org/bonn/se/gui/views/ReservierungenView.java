package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.AutoTabelle;
import org.bonn.se.gui.component.ReservierungTabelle;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.windows.AutoEintragenWindow;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.dto.ReservierungDTO;
import org.bonn.se.model.objects.entitites.ContainerEingetrageneAutos;
import org.bonn.se.model.objects.entitites.ContainerReservierungen;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.util.List;

public class ReservierungenView extends VerticalLayout implements View {

    public void setUp(){

        GridLayout mainGrid = new GridLayout(1, 7);
        mainGrid.setSizeFull();
        TopPanelUser topPanel = new TopPanelUser();

        Kunde kunde = (Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE);

        ContainerReservierungen containerReservierungen = ContainerReservierungen.getInstance();
        containerReservierungen.loadByKundennummer(kunde.getKundennummer());
        List<ReservierungDTO> autoListe = containerReservierungen.getListe();

        ReservierungTabelle<ReservierungDTO> gAutos = new ReservierungTabelle<ReservierungDTO>(autoListe);
        gAutos.setSizeFull();


        mainGrid.addComponent(topPanel,0,1,0,1);

        Label lPatzhalter1= new Label("&nbsp", ContentMode.HTML);
        mainGrid.addComponent(lPatzhalter1,0,2,0,2);

        Label lPatzhalter2= new Label("&nbsp", ContentMode.HTML);
        mainGrid.addComponent(lPatzhalter2,0,4,0,4);

        mainGrid.addComponent(gAutos,0,5,0,5);

        mainGrid.setComponentAlignment(gAutos,Alignment.BOTTOM_CENTER);
        this.addComponent(mainGrid);
        System.out.println("ResView hier1");
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

       if(UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
           this.setUp();
       } else if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }


    }


}


