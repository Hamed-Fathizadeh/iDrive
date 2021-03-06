package org.bonn.se.gui.component;

import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.ReservierungControl;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.dto.ReservierungDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AutoReservierenWindow extends CustomWindow {

   public AutoReservierenWindow(AutoEintragDTO autoEintragDTO){
       setUp(autoEintragDTO);
   }

    public void setUp(AutoEintragDTO autoEintragDTO){
        center();
        this.setWidth("90%");
        this.setHeight("80%");
        this.setModal(true);
        this.setResizable(false);

        Panel panel = new Panel();
        panel.setWidthFull();

        GridLayout gridLayout = new GridLayout(5, 29);
        gridLayout.setWidthFull();
        gridLayout.setHeightUndefined();
        gridLayout.setMargin(true);

        Label marke = new Label("<b>Marke</b>", ContentMode.HTML);
        Label modell = new Label("<b>Modell</b>", ContentMode.HTML);
        Label kurz_beschreibung = new Label("<b>Kurz beschreibung</b>", ContentMode.HTML);
        Label lang_beschreibung = new Label("<b>Lang beschreibung</b>", ContentMode.HTML);
        Label baujahr = new Label("<b>Baujahr</b>", ContentMode.HTML);
        Label automatik = new Label("<b>Automatik</b>", ContentMode.HTML);
        Label anzahl_sitzplaetze = new Label("<b>Anzahl sitzplaetze</b>", ContentMode.HTML);
        Label anzahl_tueren = new Label("<b>Anzahl Türen</b>", ContentMode.HTML);
        Label preis = new Label("<b>Preis</b>", ContentMode.HTML);
        Label klimaanlage = new Label("<b>Klimaanlage</b>", ContentMode.HTML);
        Label auto_type = new Label("<b>Auto Type</b>", ContentMode.HTML);
        Label zustand = new Label("<b>Zustand</b>", ContentMode.HTML);
        Label kilometer = new Label("<b>Kilometer</b>", ContentMode.HTML);
        Label kraftstoffart = new Label("<b>Kraftstoffart</b>", ContentMode.HTML);
        Label aussenfarbe = new Label("<b>Außenfarbe</b>", ContentMode.HTML);

        Label markeD = new Label(autoEintragDTO.getMarke());
        Label modellD = new Label(autoEintragDTO.getModell());
        Label baujahrD = new Label(""+autoEintragDTO.getBaujahr());
        Label automatikD = new Label(autoEintragDTO.isAutomatik()?"Ja":"Nein");
        Label anzahl_sitzplaetzeD = new Label(""+autoEintragDTO.getAnzahl_sitzplaetze());
        Label anzahl_tuerenD = new Label(""+autoEintragDTO.getAnzahl_tueren());
        Label preisD = new Label(""+autoEintragDTO.getPreis()+" Euro");
        Label klimaanlageD = new Label(autoEintragDTO.isKlimaanlage()?"Ja":"Nein");
        Label auto_typeD = new Label(autoEintragDTO.getAuto_type());
        Label zustandD = new Label(autoEintragDTO.getZustand());
        Label kilometerD = new Label(autoEintragDTO.getKilometer()+"km");
        Label kraftstoffartD = new Label(autoEintragDTO.getKraftstoffart());
        Label aussenfarbeD = new Label(autoEintragDTO.getAussenfarbe());

        RichTextArea kurz_beschreibungD = new RichTextArea();
        kurz_beschreibungD.setSizeFull();
        kurz_beschreibungD.setValue(autoEintragDTO.getKurz_beschreibung());
        kurz_beschreibungD.setReadOnly(true);

        RichTextArea lang_beschreibungD = new RichTextArea();
        lang_beschreibungD.setSizeFull();
        lang_beschreibungD.setValue(autoEintragDTO.getLang_beschreibung());
        lang_beschreibungD.setReadOnly(true);

        String ls1 = "<p class=MsoNormal><b><span style='font-size:20.0pt;line-height:107%;\n" +
                "font-family:\"Arial\",sans-serif;mso-ascii-theme-font:minor-bidi;mso-hansi-theme-font:\n" +
                "minor-bidi;mso-bidi-theme-font:minor-bidi;color:#2F5597;mso-themecolor:accent1;\n" +
                "mso-themeshade:191;mso-style-textfill-fill-color:#2F5597;mso-style-textfill-fill-themecolor:\n" +
                "accent1;mso-style-textfill-fill-alpha:100.0%;mso-style-textfill-fill-colortransforms:\n" +
                "lumm=75000'>Information über Ihre augewählte Auto!<o:p></o:p></span></b></p>";

        Label lSpruch = new Label(ls1, ContentMode.HTML);

        gridLayout.addComponent(lSpruch,0,0,3,0);

        gridLayout.addComponent(marke,0,9);                 gridLayout.addComponent(markeD,1,9);
        gridLayout.addComponent(modell,0,10);               gridLayout.addComponent(modellD,1,10);
        gridLayout.addComponent(baujahr,0,11);              gridLayout.addComponent(baujahrD,1,11);
        gridLayout.addComponent(automatik,0,12);            gridLayout.addComponent(automatikD,1,12);
        gridLayout.addComponent(anzahl_sitzplaetze,0,13);   gridLayout.addComponent(anzahl_sitzplaetzeD,1,13);
        gridLayout.addComponent(kilometer,0,14);            gridLayout.addComponent(kilometerD,1,14);
        gridLayout.addComponent(kraftstoffart,0,15);        gridLayout.addComponent(kraftstoffartD,1,15);

        gridLayout.addComponent(anzahl_tueren,2,9);         gridLayout.addComponent(anzahl_tuerenD,3,9);
        gridLayout.addComponent(preis,2,10);        gridLayout.addComponent(preisD,3,10);
        gridLayout.addComponent(klimaanlage,2,11);          gridLayout.addComponent(klimaanlageD,3,11);
        gridLayout.addComponent(auto_type,2,12);            gridLayout.addComponent(auto_typeD,3,12);
        gridLayout.addComponent(zustand,2,13);              gridLayout.addComponent(zustandD,3,13);
        gridLayout.addComponent(aussenfarbe,2,14);          gridLayout.addComponent(aussenfarbeD,3,14);

        Label platzhalter1 = new Label ( "&nbsp;", ContentMode.HTML);
        gridLayout.addComponent(platzhalter1,0,14);

        gridLayout.addComponent(kurz_beschreibung,0,15);
        gridLayout.addComponent(kurz_beschreibungD,0,16,4,16);

        Label platzhalter2 = new Label ( "&nbsp;", ContentMode.HTML);
        gridLayout.addComponent(platzhalter2,0,17);

        gridLayout.addComponent(lang_beschreibung,0,18);
        gridLayout.addComponent(lang_beschreibungD,0,19,4,19);

        String ls2 = "<p class=MsoNormal><b><span style='font-size:20.0pt;line-height:107%;\n" +
                "font-family:\"Arial\",sans-serif;mso-ascii-theme-font:minor-bidi;mso-hansi-theme-font:\n" +
                "minor-bidi;mso-bidi-theme-font:minor-bidi;color:#2F5597;mso-themecolor:accent1;\n" +
                "mso-themeshade:191;mso-style-textfill-fill-color:#2F5597;mso-style-textfill-fill-themecolor:\n" +
                "accent1;mso-style-textfill-fill-alpha:100.0%;mso-style-textfill-fill-colortransforms:\n" +
                "lumm=75000'> Auto reservieren &#128663<o:p></o:p></span></b></p>";

        Label lSpruch2 = new Label(ls2, ContentMode.HTML);
        gridLayout.addComponent(lSpruch2,0,20,3,20);

        gridLayout.setComponentAlignment(lSpruch, Alignment.BOTTOM_CENTER);
        gridLayout.setComponentAlignment(lSpruch2, Alignment.BOTTOM_CENTER);

        Label datePickerAbholLabel = new Label("<b>Reservierungsdatum</b>", ContentMode.HTML);
        DateTimeField datePickerAbhol = new DateTimeField ();
        datePickerAbhol.setValue (LocalDateTime.now());

        gridLayout.addComponent (datePickerAbholLabel, 0,21);
        gridLayout.addComponent (datePickerAbhol, 0,22);


        Button reservieungButton = new Button("Reservieren");
        reservieungButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        gridLayout.addComponent(reservieungButton,4,23);
        gridLayout.setComponentAlignment(reservieungButton, Alignment.BOTTOM_RIGHT);

        reservieungButton.addClickListener((Button.ClickListener) clickEvent -> {
            Window subWindow = new Window("Reservierung");
            GridLayout subContent = new GridLayout (2,2);
            subWindow.setContent(subContent);
            subWindow.setWidth("380px");
            subWindow.setHeight("100px");

            subContent.addComponent(new Label("Möchten Sie das Auto reservieren?"),0,0);
            Button neinButton = new Button("Nein");
            subContent.addComponent(neinButton,0,1);
            Button jaButton = new Button("Ja");
            subContent.addComponent(jaButton,1,1);

            subWindow.center();
            UI.getCurrent().addWindow(subWindow);

            neinButton.addClickListener((Button.ClickListener) clickEvent2 -> {
               subWindow.close();
            });

            jaButton.addClickListener((Button.ClickListener) clickEvent3 -> {

                //new LocalDateTimeToDateConverter.convertToModel(
                ReservierungDTO reservierungDTO = new ReservierungDTO();
                reservierungDTO.setAuto_id(autoEintragDTO.getAuto_id());
                reservierungDTO.setReservierungsdatum( Timestamp.valueOf(datePickerAbhol.getValue()));

                ReservierungControl.getInstance().reservieren(reservierungDTO);

                subWindow.close();
                this.close();
            });


            //UI.getCurrent().getNavigator().navigateTo(Views.REGISTER);
        });


        panel.setContent(gridLayout);

        this.setContent(panel);



    }
}
