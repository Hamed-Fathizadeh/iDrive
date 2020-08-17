package org.bonn.se.gui.component;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.control.ReservierungControl;
import org.bonn.se.model.objects.dto.ReservierungDTO;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.text.SimpleDateFormat;
import java.util.List;

    public class ReservierungTabelle <T extends ReservierungDTO> extends Grid<T> {
        List<T> data;
        private ReservierungDTO reservierungDTO;

        public void setReservierungDTO(ReservierungDTO reservierungDTO) {
            this.reservierungDTO = reservierungDTO;
        }


        public int getAnzahlRow() {
            return data.size();
        }

        public void setData(List<T> data) {
            this.removeAllColumns();
            this.data = data;
            setUp();
        }

        public ReservierungTabelle(List<ReservierungDTO> dataInput) {
            super();
            this.setHeightMode(HeightMode.UNDEFINED);
            this.addStyleName("AnzeigeUnternehmen");
            this.setSelectionMode(SelectionMode.SINGLE);
            this.setWidth("900px");
            this.setHeight("100%");
            this.setCaption("Treffer: "+ dataInput.size());
            this.setColumnReorderingAllowed(true);

            @SuppressWarnings("unchecked") SingleSelect<ReservierungDTO> selection = (SingleSelect<ReservierungDTO>) this.asSingleSelect();

            this.addSelectionListener(event -> {

                if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {

                    Window subWindow = new Window("Reservierung Stornieren");
                    GridLayout subContent = new GridLayout (3,2);
                    subWindow.setContent(subContent);
                    subWindow.setWidth("500px");
                    subWindow.setHeight("100px");



                    subContent.addComponent(new Label("Möchten Sie Ihre Reservierung Stornieren?"),0,0);




                    Button neinButton = new Button("Nein");
                    subContent.addComponent(neinButton,0,1);
                    subContent.setComponentAlignment(neinButton,Alignment.BOTTOM_RIGHT);

                    Label placeholder1 = new Label ( "&nbsp;", ContentMode.HTML);
                    subContent.addComponent(placeholder1,1,1);

                    Button stornierenButton = new Button("Ja");
                    subContent.addComponent(stornierenButton,2,1);
                    subContent.setComponentAlignment(stornierenButton,Alignment.BOTTOM_LEFT);
                    ReservierungDTO autDTOtemp = selection.getValue();

                    neinButton.addClickListener((Button.ClickListener) clickEvent -> {
                        subWindow.close();
                    });


                    stornierenButton.addClickListener((Button.ClickListener) clickEvent -> {

                    if(autDTOtemp != null){
                        setReservierungDTO(autDTOtemp);
                    }
                        ReservierungControl.getInstance().stornieren(autDTOtemp);
                        subWindow.close();
                        org.bonn.se.gui.window.ConfirmationWindow confWindow =  new org.bonn.se.gui.window.ConfirmationWindow("Die Reservierung wurde Storniert!");
                        UI.getCurrent().addWindow(confWindow);
                        confWindow.focus();
                        UI.getCurrent().getNavigator().navigateTo(Views.RESERVIERUNGENVIEW);
                    });


                    subWindow.center();

                    UI.getCurrent().addWindow(subWindow);



                }else{


                    ReservierungDTO bewDTOtemp = selection.getValue();
                    if(bewDTOtemp != null){
                        setReservierungDTO(bewDTOtemp);
                    }

                    //BewerbungWindow bewerbungWindow = new BewerbungWindow(null, "Unternehmen", bewerbungDTO);

                    //  UI.getCurrent().addWindow(bewerbungWindow);

                }
            });
            data = (List<T>) dataInput;
            setUp();

        }

        public void setUp(){


            this.removeAllColumns();
            this.setItems( data);

                this.addColumn(ReservierungDTO::getMarke).setCaption("Marke").setWidth(150.0);
                this.addColumn(ReservierungDTO::getModell).setCaption("Modell").setWidth(150);
                this.addColumn(ReservierungDTO::getKurz_beschreibung).setCaption("Kurz Beschreibung").setWidth(400);
                //this.addColumn(ReservierungDTO::getBaujahr).setCaption("Baujahr").setWidth(150);
                //this.addColumn(auto ->(auto.isAutomatik()? "Ja":"Nein")).setCaption("Automatik").setWidth(150);
                //this.addColumn(ReservierungDTO::getAnzahl_tueren).setCaption("Anzahl Türen").setWidth(150);
                //this.addColumn(auto ->(auto.isKlimaanlage()? "Ja":"Nein")).setCaption("Klimaanlage").setWidth(150);
                this.addColumn(ReservierungDTO::getAuto_type).setCaption("Auto Type").setWidth(150);
                this.addColumn(p -> {
                String  sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(p.getAbholdatum());
                return sdf;
                }).setCaption("Abholdatum").setWidth(190);

                this.addColumn(p -> {
                String  sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(p.getRueckgabedatum());
                return sdf;
                }).setCaption("Rückgabedatum").setWidth(190);
                this.addColumn(ReservierungDTO::getAnzahl_sitzplaetze).setCaption("Anzahl Sitzplaetze").setWidth(150);
                this.addColumn(ReservierungDTO::getAnzahl_tueren).setCaption("Anzahl Türen").setWidth(150);



        }




    }

