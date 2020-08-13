package org.bonn.se.gui.windows;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.component.CustomWindow;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.model.objects.entitites.Auto;
import org.bonn.se.model.objects.entitites.User;

public class AutoEintragenWindow extends CustomWindow {

    public AutoEintragenWindow(){
        setUp();
    }

    public void setUp()  {
        center();
        this.setWidth("90%");
        this.setHeight("80%");
        this.setModal(true);
        this.setResizable(false);
        this.setClosable(false);


        GridLayout gridLayout = new GridLayout(3, 17);
        gridLayout.setWidthFull();
        gridLayout.setHeightUndefined();
        gridLayout.setMargin(true);

        //schrifft
        String ls1 = "<div class=WordSection1>\n" +
                "\n" +
                "<p class=MsoNormal><b><span style='font-size:36.0pt;line-height:107%;\n" +
                "font-family:\"\"YACgEa4Wckw 0\", _fb_, auto\";color:#003853'>Auto Eintragen!<span\n" +
                "class=SpellE> &#128663</span><o:p></o:p></span></b></p>\n" +
                "</div>";

        Label head = new Label(ls1, ContentMode.HTML);

        ComboBox<String> comboMarke = new ComboBox<>();
        comboMarke.setPlaceholder("Marke");
        comboMarke.setWidth(300.0f, Unit.PIXELS);
        //SuchbegrifService sService = new SuchbegrifService();
        //comboNachWas.setDataProvider(sService::fetch, sService::count);

        ComboBox<String> comboModell = new ComboBox<>();
        comboModell.setPlaceholder("Modell");
        comboModell.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboBaujahr = new ComboBox<>();
        comboBaujahr.setPlaceholder("Baujahr");
        comboBaujahr.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboAutomatik = new ComboBox<>();
        comboAutomatik.setPlaceholder("Automatik");
        comboAutomatik.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboKlimaanlage = new ComboBox<>();
        comboKlimaanlage.setPlaceholder("Klimaanlage");
        comboKlimaanlage.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboAnzahlSitze = new ComboBox<>();
        comboAnzahlSitze.setPlaceholder("Anzahl Sitze");
        comboAnzahlSitze.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboAnzahlTuere = new ComboBox<>();
        comboAnzahlTuere.setPlaceholder("Anzahl Türe");
        comboAnzahlTuere.setWidth(300.0f, Unit.PIXELS);

        ComboBox<String> comboAutoType = new ComboBox<>();
        comboAutoType.setPlaceholder("Auto Type");
        comboAutoType.setWidth(300.0f, Unit.PIXELS);

        RegistrationTextField preis = new RegistrationTextField("Preis Pro Tag");
       // preis.selectAll();
       // preis.setValue(VaadinService.getCurrent().getBaseDirectory().toString());

        RegistrationTextField kennzeichen = new RegistrationTextField("Autokennzeichen");
        kennzeichen.selectAll();
        kennzeichen.setValue(VaadinService.getCurrent().getBaseDirectory().toString());

        Binder<Auto> binder = new Binder<>(Auto.class);

        binder.forField(comboMarke)
                .asRequired("Marke muss angegeben werden!")
                .bind(Auto::getMarke,Auto::setMarke);

        binder.forField(comboModell)
                .asRequired("Modell muss angegeben werden!")
                .bind(Auto::getModell,Auto::setModell);

        Auto auto = new Auto();

        binder.setBean(auto);

        gridLayout.addComponent(head,0,0,2,0);

        gridLayout.addComponent(comboMarke,0,1);
        gridLayout.addComponent(comboModell,1,1);
        gridLayout.addComponent(comboBaujahr,2,1);

        gridLayout.addComponent(comboAutomatik,0,2);
        gridLayout.addComponent(comboKlimaanlage,1,2);
        gridLayout.addComponent(comboAnzahlSitze,2,2);

        gridLayout.addComponent(comboAnzahlTuere,0,3);
        gridLayout.addComponent(comboAutoType,1,3);
        //gridLayout.addComponent(preis,2,2);

        gridLayout.addComponent(kennzeichen,0,4);
        gridLayout.addComponent(preis,1,4);

        gridLayout.setComponentAlignment(head, Alignment.TOP_CENTER);
        gridLayout.setComponentAlignment(comboMarke, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboModell, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboBaujahr, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAutomatik, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboKlimaanlage, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAnzahlSitze, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAnzahlTuere, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAutoType, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(kennzeichen, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(preis, Alignment.MIDDLE_CENTER);

        Panel panel = new Panel();
        panel.setWidthFull();
        panel.setContent(gridLayout);
        this.setContent(panel);


    }
}
