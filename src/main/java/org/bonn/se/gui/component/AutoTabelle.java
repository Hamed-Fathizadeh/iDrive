package org.bonn.se.gui.component;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.control.AutoEintragControl;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.ContainerEingetrageneAutos;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.util.List;

public class AutoTabelle <T extends AutoEintragDTO> extends Grid<T> {
    List<T> data;
    private AutoEintragDTO autoEintragDTO;

    public void setAutoEintragDTO(AutoEintragDTO bewerbungDTO) {
        this.autoEintragDTO = bewerbungDTO;
    }


    public int getAnzahlRow() {
        return data.size();
    }

    public void setData(List<T> data) {
        this.removeAllColumns();
        this.data = data;
        setUp();
    }

    public AutoTabelle(List<AutoEintragDTO> dataInput) {
        super();
        this.setHeightMode(HeightMode.UNDEFINED);
        this.addStyleName("AnzeigeUnternehmen");
        this.setSelectionMode(SelectionMode.SINGLE);
        this.setWidth("900px");
        this.setHeight("100%");
        this.setCaption("Treffer: "+ dataInput.size());
        this.setColumnReorderingAllowed(true);

        @SuppressWarnings("unchecked") SingleSelect<AutoEintragDTO> selection = (SingleSelect<AutoEintragDTO>) this.asSingleSelect();

        this.addSelectionListener(event -> {

            if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {

                Window subWindow = new Window("Eintrag Löschen");
                GridLayout subContent = new GridLayout (2,2);
                subWindow.setContent(subContent);
                subWindow.setWidth("600px");
                subWindow.setHeight("100px");



                subContent.addComponent(new Label("Möchten Sie den Eintrag Löschen?"),0,0);


                Button loeschen = new Button("Löschen");
                subContent.addComponent(loeschen,1,1);
                AutoEintragDTO autDTOtemp = selection.getValue();


                loeschen.addClickListener((Button.ClickListener) clickEvent -> {

                    /*if(autDTOtemp != null){
                        setBewerbungDTO(autDTOtemp);
                    }*/
                    try {
                        AutoEintragControl.autoEintragenLoeschen(autDTOtemp);
                    } catch (DatabaseException e) {
                        e.printStackTrace();
                    }
                    subWindow.close();
                    org.bonn.se.gui.window.ConfirmationWindow confWindow =  new org.bonn.se.gui.window.ConfirmationWindow("Der Eintrag wurde gelöscht");
                    UI.getCurrent().addWindow(confWindow);
                    confWindow.focus();
                    UI.getCurrent().getNavigator().navigateTo(Views.VERTIEBLERHOMEVIEW);
                });


                subWindow.center();

                UI.getCurrent().addWindow(subWindow);



            }else{


                AutoEintragDTO bewDTOtemp = selection.getValue();
                if(bewDTOtemp != null){
                    setAutoEintragDTO(bewDTOtemp);
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


        if(UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            this.addColumn(AutoEintragDTO::getMarke).setCaption("Marke").setWidth(80.0);
            this.addColumn(AutoEintragDTO::getModell).setCaption("Modell").setWidth(150);
            this.addColumn(AutoEintragDTO::getKurz_beschreibung).setCaption("Kurz Beschreibung").setWidth(400);
            this.addColumn(AutoEintragDTO::getBaujahr).setCaption("Baujahr").setWidth(150);
            this.addColumn(auto ->(auto.isAutomatik()? "Ja":"Nein")).setCaption("Automatik").setWidth(150);
            this.addColumn(AutoEintragDTO::getAnzahl_sitzplaetze).setCaption("Anzahl Sitzplaetze").setWidth(150);
            this.addColumn(AutoEintragDTO::getAnzahl_tueren).setCaption("Anzahl Türen").setWidth(150);
            this.addColumn(auto ->(auto.isKlimaanlage()? "Ja":"Nein")).setCaption("Klimaanlage").setWidth(150);
            this.addColumn(AutoEintragDTO::getAuto_type).setCaption("Auto Type").setWidth(150);

        }else{
         /*   this.addComponentColumn(BewerbungDTO::getStudentPicture).setCaption("Bild");
            this.addColumn(BewerbungDTO::getStudentVorname).setCaption("Vorname");
            this.addColumn(BewerbungDTO::getStudentNachname).setCaption("Nachname");
            this.addColumn(BewerbungDTO::getStudentStudiengang).setCaption("Studiengang");
            this.addColumn(BewerbungDTO::getStudentHoesterAbschluss).setCaption("Höchster Abschluss");
            this.addColumn(BewerbungDTO::getStudentAusbildung).setCaption("Ausbildung");
            this.addComponentColumn(bew -> (bew.isBewerbungMarkiert() ? ImageConverter.getMarkierung() : null)).setCaption("Markiert");
            this.addComponentColumn(bew -> (bew.getStatus() == 9 ? new Label(" <style>p { color:red ; font-weight:bold;  font-size: 18px; }</style><p>Neu</p>", ContentMode.HTML): null)).setCaption("");
*/
        } new Label("<b>Unternehmensname</b>", ContentMode.HTML);



    }




}
