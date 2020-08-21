package org.bonn.se.gui.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.AutoEintragControl;
import org.bonn.se.gui.component.CustomWindow;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.*;
import org.bonn.se.services.util.AutoMarkeService;
import org.bonn.se.services.util.AutoModellService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        //this.setClosable(false);


        GridLayout gridLayout = new GridLayout(3, 23);
        gridLayout.setWidthFull();
        //gridLayout.setHeightUndefined();
        gridLayout.setMargin(true);

        //schrifft
        String ls1 = "<div class=WordSection1>\n" +
                "\n" +
                "<p class=MsoNormal><b><span style='font-size:36.0pt;line-height:107%;\n" +
                "font-family:\"\"YACgEa4Wckw 0\", _fb_, auto\";color:#003853'>Auto Eintragen!<span\n" +
                "class=SpellE> &#128663</span><o:p></o:p></span></b></p>\n" +
                "</div>";

        Label head = new Label(ls1, ContentMode.HTML);

        ComboBox<String> comboMarke = new ComboBox<>("Marke");
        comboMarke.setWidth(300.0f, Unit.PIXELS);
        AutoMarkeService ServiceMarke = new AutoMarkeService();
        comboMarke.setDataProvider(ServiceMarke::fetch, ServiceMarke::count);
        comboMarke.setPlaceholder("BMW");

        ComboBox<String> comboModell = new ComboBox<>("Modell");
        comboModell.setWidth(300.0f, Unit.PIXELS);
        comboModell.setPlaceholder("X3");


        comboMarke.addValueChangeListener((HasValue.ValueChangeListener<String>) event -> {
        AutoModellService ServiceModell = new AutoModellService(comboMarke.getValue());
        comboModell.setDataProvider(ServiceModell::fetch, ServiceModell::count);
        });
        ComboBox<Integer> comboBaujahr = new ComboBox<>("Baujahr");
        comboBaujahr.setWidth(300.0f, Unit.PIXELS);

        DateField date = new DateField();
        date.setValue(LocalDate.now());
        date.setDateFormat("yyyy-MM-dd");
        System.out.println(date.getValue().getYear());
        List<Integer> lBaujahr = new ArrayList<Integer>();
        for(int i = date.getValue().getYear(); i >= 1970;i-- ){
            lBaujahr.add(i);
        }
        comboBaujahr.setItems(lBaujahr);


        ComboBox<Boolean> comboAutomatik = new ComboBox<>("Automatik");
        comboAutomatik.setWidth(300.0f, Unit.PIXELS);
        comboAutomatik.setItems(true,false);

        ComboBox<Boolean> comboKlimaanlage = new ComboBox<>("Klimaanlage");
        comboKlimaanlage.setWidth(300.0f, Unit.PIXELS);
        comboKlimaanlage.setItems(true,false);

        ComboBox<Integer> comboAnzahlSitze = new ComboBox<>("Anzahl Sitze");
        comboAnzahlSitze.setWidth(300.0f, Unit.PIXELS);
        comboAnzahlSitze.setItems(1,2,3,4,5,6,7,8,9);

        ComboBox<Integer> comboAnzahlTuere = new ComboBox<>("Anzahl Türe");
        comboAnzahlTuere.setWidth(300.0f, Unit.PIXELS);
        comboAnzahlTuere.setItems(1,2,3,4,5);

        ComboBox<String> comboAutoType = new ComboBox<>("Auto Type");
        comboAutoType.setWidth(300.0f, Unit.PIXELS);
        comboAutoType.setItems("Cabrio","Limousine","SUV","Coupe","Kombi","Van","Transporter");
        comboAutoType.setPlaceholder("SUV");

        ComboBox<String> comboZustand = new ComboBox<>("Zustand");
        comboZustand.setWidth(300.0f, Unit.PIXELS);
        comboZustand.setItems("Neu","Gebraucht","Tageszulassung","Jahreswagen","Oldtimer","Vorführfahrzeug","Fahrtauglich");
        comboZustand.setPlaceholder("Neu");

        ComboBox<String> comboKraftstoffart = new ComboBox<>("Kraftstoffart");
        comboKraftstoffart.setWidth(300.0f, Unit.PIXELS);
        comboKraftstoffart.setItems("Benzin","Diesel","Elektro","Hybrid (Benzin/Elektro)","Hybrid (Diesel / Elektro)","Autogas (LPG)",
                               "Ethanol (FFV, E85 etc.)", "Wasserstoff");
        comboKraftstoffart.setPlaceholder("Benzin");

        ComboBox<String> comboFarbe = new ComboBox<>("Außenfarbe");
        comboFarbe.setWidth(300.0f, Unit.PIXELS);
        comboFarbe.setItems("Beige","Schwarz","Blau","Braun","Gold","Grün","Grau", "Orange", "Violett", "Rot",
                               "Silber", "Weiß", "Gelb", "Metallic");
        comboFarbe.setPlaceholder("Neu");

        ComboBox<Integer> preis = new ComboBox("Preis");
        preis.setWidth(300.0f, Unit.PIXELS);
        List<Integer> lPreis = new ArrayList<Integer>();
        for(int i = 500; i <= 100000; i+= 500 ){
            lPreis.add(i);
        }
        preis.setItems(lPreis);

        ComboBox<Integer> kilometer = new ComboBox("Kilometer");
        kilometer.setWidth(300.0f, Unit.PIXELS);
        List<Integer> lkilometer = new ArrayList<Integer>();
        for(int i = 0; i <= 150000; i+= 5000 ){
            lkilometer.add(i);
        }
        kilometer.setItems(lkilometer);

        Binder<Auto> binder = new Binder<>(Auto.class);

        binder.forField(comboMarke)
                .asRequired("Marke muss angegeben werden!")
                .bind(Auto::getMarke,Auto::setMarke);

        binder.forField(comboModell)
                .asRequired("Modell muss angegeben werden!")
                .bind(Auto::getModell,Auto::setModell);

        binder.forField(comboBaujahr)
                .asRequired("Baujahr muss angegeben werden!")
                .bind(Auto::getBaujahr,Auto::setBaujahr);


        binder.forField(comboAutomatik)
                .asRequired("Ist es Automatik?")
                .bind(Auto::isAutomatik,Auto::setAutomatik);


        binder.forField(comboKlimaanlage)
                .asRequired("Hat es einen Klimaanlage?")
                .bind(Auto::isKlimaanlage,Auto::setKlimaanlage);


        binder.forField(comboAnzahlSitze)
                .asRequired("Anzahl Sitzplätze  muss angegeben werden!")
                .bind(Auto::getAnzahl_sitzplaetze,Auto::setAnzahl_sitzplaetze);


        binder.forField(comboAnzahlTuere)
                .asRequired("Anzahl Türen  muss angegeben werden!")
                .bind(Auto::getAnzahl_tueren,Auto::setAnzahl_tueren);

        binder.forField(comboAutoType)
                .asRequired("Auto Type  muss angegeben werden!")
                .bind(Auto::getAuto_type,Auto::setAuto_type);

        binder.forField(preis)
                .asRequired("Preis  muss angegeben werden!")
                .bind(Auto::getPreis,Auto::setPreis);

        binder.forField(comboZustand)
                .asRequired("Zustand  muss angegeben werden!")
                .bind(Auto::getZustand,Auto::setZustand);

        binder.forField(comboKraftstoffart)
                .asRequired("Kraftstoff  muss angegeben werden!")
                .bind(Auto::getKraftstoffart,Auto::setKraftstoffart);

        binder.forField(comboFarbe)
                .asRequired("Farbe  muss angegeben werden!")
                .bind(Auto::getAussenfarbe,Auto::setAussenfarbe);

        binder.forField(kilometer)
                .asRequired("Kilometer  muss angegeben werden!")
                .bind(Auto::getKilometer,Auto::setKilometer);



        Auto auto = new Auto();
        binder.setBean(auto);



        gridLayout.addComponent(head,0,0,2,0);

        gridLayout.addComponent(comboMarke,0,1);
        gridLayout.addComponent(comboModell,1,1);
        gridLayout.addComponent(comboBaujahr,2,1);

        Label lPatzhalter1 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter1,0,2,2,2);

        gridLayout.addComponent(comboAutomatik,0,3);
        gridLayout.addComponent(comboKlimaanlage,1,3);
        gridLayout.addComponent(comboAnzahlSitze,2,3);

        Label lPatzhalter2 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter2,0,4,2,4);

        gridLayout.addComponent(comboAnzahlTuere,0,5);
        gridLayout.addComponent(comboAutoType,1,5);
        gridLayout.addComponent(comboZustand,2,5);

        Label lPatzhalter3 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter3,0,6,2,6);

        gridLayout.addComponent(comboKraftstoffart,0,7);
        gridLayout.addComponent(comboFarbe,1,7);
        gridLayout.addComponent(kilometer,2,7);

        Label lPatzhalter4 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter4,0,8,2,8);

        gridLayout.addComponent(preis,0,9);

        TextArea richTextAreaKurz = new TextArea("Kurze Beschreibung");
        richTextAreaKurz.setWidthFull();
        richTextAreaKurz.setHeight("300px");
        richTextAreaKurz.setValue("Schreiben Sie hier eine kurze Beschriebung!");

        Label lPatzhalter5 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter5,0,10,2,10);

        gridLayout.addComponent(richTextAreaKurz,0,11,2,11);

        RichTextArea richTextAreaLang = new RichTextArea("Lange Beschreibung");
        richTextAreaLang.setWidthFull();
        richTextAreaLang.setHeight("600px");
        richTextAreaLang.setValue("<h1>Schreiben Sie hier eine lange Beschriebung!</h1>");

        Label lPatzhalter6 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter6,0,12,2,12);

        gridLayout.addComponent(richTextAreaLang,0,13,2,13);

        Label lPatzhalter7= new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter7,0,14,2,14);

        Button autoEintragenButton = new Button("Auto Eintragen");

        gridLayout.addComponent(autoEintragenButton,2,15);

        autoEintragenButton.addClickListener(
                event -> {
                    AutoEintragDTO autoEintragDTO = new AutoEintragDTO();
                    autoEintragDTO.setMarke(auto.getMarke());
                    autoEintragDTO.setModell(auto.getModell());
                    autoEintragDTO.setBaujahr(comboBaujahr.getValue());
                    autoEintragDTO.setAutomatik(comboAutomatik.getValue());
                    autoEintragDTO.setKlimaanlage(comboKlimaanlage.getValue());
                    autoEintragDTO.setAnzahl_sitzplaetze(comboAnzahlSitze.getValue());
                    autoEintragDTO.setAnzahl_tueren(comboAnzahlTuere.getValue());
                    autoEintragDTO.setAuto_type(comboAutoType.getValue());
                    autoEintragDTO.setPreis(preis.getValue());
                    autoEintragDTO.setZustand(comboZustand.getValue());
                    autoEintragDTO.setKurz_beschreibung(richTextAreaKurz.getValue());
                    autoEintragDTO.setLang_beschreibung(richTextAreaLang.getValue());
                    autoEintragDTO.setAussenfarbe(comboFarbe.getValue());
                    autoEintragDTO.setKilometer(kilometer.getValue());
                    autoEintragDTO.setKraftstoffart(comboKraftstoffart.getValue());



                    try {
                        AutoEintragControl.autoEintragen(autoEintragDTO);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    this.close();

                });


        gridLayout.setComponentAlignment(head, Alignment.TOP_CENTER);
        gridLayout.setComponentAlignment(comboMarke, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboModell, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboBaujahr, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAutomatik, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboKlimaanlage, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAnzahlSitze, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAnzahlTuere, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboAutoType, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboZustand, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboKraftstoffart, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(kilometer, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(comboFarbe, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(preis, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(autoEintragenButton, Alignment.MIDDLE_RIGHT);




        Panel panel = new Panel();
        panel.setWidthFull();

        panel.setContent(gridLayout);
        this.setContent(panel);





    }
}
