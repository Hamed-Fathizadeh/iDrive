package org.bonn.se.gui.windows;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.AutoEintragControl;
import org.bonn.se.gui.component.CustomWindow;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.*;

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


        GridLayout gridLayout = new GridLayout(3, 17);
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
        comboMarke.setItems("Acura");//temp
        //SuchbegrifService sService = new SuchbegrifService();
        //comboNachWas.setDataProvider(sService::fetch, sService::count);

        ComboBox<String> comboModell = new ComboBox<>("Modell");
        comboModell.setWidth(300.0f, Unit.PIXELS);
        comboModell.setItems("CL");//temp

        ComboBox<String> comboBaujahr = new ComboBox<>("Baujahr");
        comboBaujahr.setWidth(300.0f, Unit.PIXELS);

        DateField date = new DateField();
        date.setValue(LocalDate.now());
        date.setDateFormat("yyyy-MM-dd");
        System.out.println(date.getValue().getYear());
        List<String> lBaujahr = new ArrayList<String>();
        for(int i = date.getValue().getYear(); i >= 1980;i-- ){
            lBaujahr.add(i+"");
        }
        comboBaujahr.setItems(lBaujahr);

        ComboBox<String> comboAutomatik = new ComboBox<>("Automatik");
        comboAutomatik.setWidth(300.0f, Unit.PIXELS);
        comboAutomatik.setItems("Ja","Nein");

        ComboBox<String> comboKlimaanlage = new ComboBox<>("Klimaanlage");
        comboKlimaanlage.setWidth(300.0f, Unit.PIXELS);
        comboKlimaanlage.setItems("Ja","Nein");

        ComboBox<String> comboAnzahlSitze = new ComboBox<>("Anzahl Sitze");
        comboAnzahlSitze.setWidth(300.0f, Unit.PIXELS);
        comboAnzahlSitze.setItems("1","2","3","4","5","6","7","8","9");

        ComboBox<String> comboAnzahlTuere = new ComboBox<>("Anzahl Türe");
        comboAnzahlTuere.setWidth(300.0f, Unit.PIXELS);
        comboAnzahlTuere.setItems("1","2","3","4","5");

        ComboBox<String> comboAutoType = new ComboBox<>("Auto Type");
        comboAutoType.setWidth(300.0f, Unit.PIXELS);
        comboAutoType.setItems("Cabrio","Limousine","SUV","Coupe","Kombi","Van","Transporter");

        RegistrationTextField preis = new RegistrationTextField("Preis Pro Tag");
        preis.setWidth(300.0f, Unit.PIXELS);

        RegistrationTextField kennzeichen = new RegistrationTextField("Autokennzeichen");
        kennzeichen.setWidth(300.0f, Unit.PIXELS);

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

        Label lPatzhalter1 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter1,0,2,2,2);

        gridLayout.addComponent(comboAutomatik,0,3);
        gridLayout.addComponent(comboKlimaanlage,1,3);
        gridLayout.addComponent(comboAnzahlSitze,2,3);

        Label lPatzhalter2 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter2,0,4,2,4);

        gridLayout.addComponent(comboAnzahlTuere,0,5);
        gridLayout.addComponent(comboAutoType,1,5);

        Label lPatzhalter3 = new Label("&nbsp", ContentMode.HTML);
        gridLayout.addComponent(lPatzhalter3,0,6,2,6);

        gridLayout.addComponent(kennzeichen,0,7);

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
                    autoEintragDTO.setBaujahr(Integer.parseInt(comboBaujahr.getValue()));
                    autoEintragDTO.setAutomatik(comboAutomatik.getValue().equals("Ja")?true:false);
                    autoEintragDTO.setKlimaanlage(comboKlimaanlage.getValue().equals("Ja")?true:false);
                    autoEintragDTO.setAnzahl_sitzplaetze(Integer.parseInt(comboAnzahlSitze.getValue()));
                    autoEintragDTO.setAnzahl_tueren(Integer.parseInt(comboAnzahlTuere.getValue()));
                    autoEintragDTO.setAuto_type(comboAutoType.getValue());
                    autoEintragDTO.setPreis_pro_tag(Double.parseDouble(preis.getValue()));
                    autoEintragDTO.setAutokennzeichen(kennzeichen.getValue());
                    autoEintragDTO.setKurz_beschreibung(richTextAreaKurz.getValue());
                    autoEintragDTO.setLang_beschreibung(richTextAreaLang.getValue());



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
        gridLayout.setComponentAlignment(kennzeichen, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(preis, Alignment.MIDDLE_CENTER);
        gridLayout.setComponentAlignment(autoEintragenButton, Alignment.MIDDLE_RIGHT);




        Panel panel = new Panel();
        panel.setWidthFull();

        panel.setContent(gridLayout);
        this.setContent(panel);



    }
}
