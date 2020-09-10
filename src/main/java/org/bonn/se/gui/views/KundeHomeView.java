package org.bonn.se.gui.views;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.control.Suche;
import org.bonn.se.control.SucheControlProxy;
import org.bonn.se.gui.component.AutoTabelle;
import org.bonn.se.gui.component.RegistrationTextField;
import org.bonn.se.gui.component.TopPanelUser;
import org.bonn.se.gui.windows.AutoReservierenWindow;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.ContainerEingetrageneAutos;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.AutoMarkeService;
import org.bonn.se.services.util.AutoModellService;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KundeHomeView  extends VerticalLayout implements View {



    private String suchArt = "Einfache";
    final Suche suche = new SucheControlProxy();

    public void setUp()throws DatabaseException, SQLException {

        GridLayout mainGrid = new GridLayout(2, 7);
        mainGrid.setSizeFull();
        TopPanelUser topPanel = new TopPanelUser();

        String ls3 = "<p class=MsoNormal><b><span style='font-size:20.0pt;line-height:107%;\n" +
                "font-family:\"Arial\",sans-serif;mso-ascii-theme-font:minor-bidi;mso-hansi-theme-font:\n" +
                "minor-bidi;mso-bidi-theme-font:minor-bidi;color:#2F5597;mso-themecolor:accent1;\n" +
                "mso-themeshade:191;mso-style-textfill-fill-color:#2F5597;mso-style-textfill-fill-themecolor:\n" +
                "accent1;mso-style-textfill-fill-alpha:100.0%;mso-style-textfill-fill-colortransforms:\n" +
                "lumm=75000'>&#128663 Auto suchen und reservieren &#128663<o:p></o:p></span></b></p>";

        Label lSpruch = new Label(ls3, ContentMode.HTML);

        GridLayout searchGrid = new GridLayout(8, 6);
        searchGrid.setMargin(true);
        searchGrid.setSizeFull();

        //--------------------------------------------SUCHE------------------------------------------------------------
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

        ComboBox<String> comboModell = new ComboBox<>("Modell");
        comboModell.setWidth(300.0f, Unit.PIXELS);

        comboMarke.addValueChangeListener((HasValue.ValueChangeListener<String>) event -> {
            AutoModellService ServiceModell = new AutoModellService(comboMarke.getValue());
            comboModell.setDataProvider(ServiceModell::fetch, ServiceModell::count);
        });
        ComboBox<String> comboBaujahr = new ComboBox<>("Baujahr ab");
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



        ComboBox<String> comboAutoType = new ComboBox<>("Auto Type");
        comboAutoType.setWidth(300.0f, Unit.PIXELS);
        comboAutoType.setItems("Cabrio","Limousine","SUV","Coupe","Kombi","Van","Transporter");

        RegistrationTextField kennzeichen = new RegistrationTextField("Autokennzeichen");
        kennzeichen.setWidth(300.0f, Unit.PIXELS);

        CheckBox erwSuche = new CheckBox("Erweiterte Suche");

        ComboBox<String> comboKlimaanlage = new ComboBox<>("Klimaanlage");
        ComboBox<String> comboAnzahlSitze = new ComboBox<>("Anzahl Sitze ab");
        ComboBox<String> comboAnzahlTuere = new ComboBox<>("Anzahl Türe ab");
        ComboBox<String> comboZustand = new ComboBox<>("Zustand");
        ComboBox<String> comboKraftstoffart = new ComboBox<>("Kraftstoffart");
        ComboBox<String> comboFarbe = new ComboBox<>("Außenfarbe");
        ComboBox<Integer> kilometer = new ComboBox("Kilometer bis");
        ComboBox<Integer> preis = new ComboBox("Preis bis");
        ComboBox<String> comboAutomatik = new ComboBox<>("Automatik");







        comboKlimaanlage.setVisible(false);
        comboAnzahlSitze.setVisible(false);
        comboAnzahlTuere.setVisible(false);
        comboZustand.setVisible(false);
        comboKraftstoffart.setVisible(false);
        comboFarbe.setVisible(false);
        kilometer.setVisible(false);
        preis.setVisible(false);
        comboAutomatik.setVisible(false);

        //GridLayout Suche
        searchGrid.addComponent(lSpruch,0,0,6,0);
        searchGrid.addComponent(comboMarke,2,1);
        searchGrid.addComponent(comboModell,3,1);
        searchGrid.addComponent(comboBaujahr,4,1);
        searchGrid.addComponent(comboKlimaanlage,2,2);
        searchGrid.addComponent(comboAnzahlSitze,3,2);
        searchGrid.addComponent(comboAnzahlTuere,4,2);
        searchGrid.addComponent(comboZustand,2,3);
        searchGrid.addComponent(comboKraftstoffart,3,3);
        searchGrid.addComponent(comboFarbe,4,3);
        searchGrid.addComponent(kilometer,2,4);
        searchGrid.addComponent(preis,3,4);
        searchGrid.addComponent(comboAutomatik,4,4);
        searchGrid.addComponent(erwSuche,6,5);

        searchGrid.setComponentAlignment(lSpruch, Alignment.BOTTOM_CENTER);
       // searchGrid.setComponentAlignment(comboOrtBund, Alignment.BOTTOM_LEFT);
       // searchGrid.setComponentAlignment(comboUmkreis, Alignment.BOTTOM_LEFT);
       // searchGrid.setComponentAlignment(lSpruch, Alignment.TOP_CENTER);

        //Ergebnis Tabelle
        Grid<AutoEintragDTO> grid1 = new Grid<>();
        DataProvider<AutoEintragDTO, Void> dataProviderInit = suche.einfacheSuche(null, null, "0",
                "Erweitert", null, null, null, null,
                null, null, 999999999 , 0,null);
        grid1.setDataProvider(dataProviderInit);

        //Erweiterte Suche An/Aus
        erwSuche.addValueChangeListener((HasValue.ValueChangeListener<Boolean>) event -> {
            if(erwSuche.getValue()) {
                suchArt = "Erweitert";

                //Klimaanlage
                comboKlimaanlage.setWidth(300.0f, Unit.PIXELS);
                comboKlimaanlage.setItems("Ja", "Nein");

                //AnzahlSitze
                comboAnzahlSitze.setWidth(300.0f, Unit.PIXELS);
                comboAnzahlSitze.setItems("1", "2", "3", "4", "5", "6", "7", "8", "9");

                //AnzahlTuere
                comboAnzahlTuere.setWidth(300.0f, Unit.PIXELS);
                comboAnzahlTuere.setItems("1", "2", "3", "4", "5");

                comboZustand.setWidth(300.0f, Unit.PIXELS);
                comboZustand.setItems("Neu","Gebraucht","Tageszulassung","Jahreswagen","Oldtimer","Vorführfahrzeug","Fahrtauglich");

                comboKraftstoffart.setWidth(300.0f, Unit.PIXELS);
                comboKraftstoffart.setItems("Benzin","Diesel","Elektro","Hybrid (Benzin/Elektro)","Hybrid (Diesel / Elektro)","Autogas (LPG)",
                        "Ethanol (FFV, E85 etc.)", "Wasserstoff");

                comboFarbe.setWidth(300.0f, Unit.PIXELS);
                comboFarbe.setItems("Beige","Schwarz","Blau","Braun","Gold","Grün","Grau", "Orange", "Violett", "Rot",
                        "Silber", "Weiß", "Gelb", "Metallic");

                preis.setWidth(300.0f, Unit.PIXELS);
                List<Integer> lPreis = new ArrayList<Integer>();
                for(int i = 500; i <= 100000; i+= 500 ){
                    lPreis.add(i);
                }
                preis.setItems(lPreis);

                kilometer.setWidth(300.0f, Unit.PIXELS);
                List<Integer> lkilometer = new ArrayList<Integer>();
                for(int i = 0; i <= 150000; i+= 5000 ){
                    lkilometer.add(i);
                }
                kilometer.setItems(lkilometer);

                comboAutomatik.setWidth(300.0f, Unit.PIXELS);
                comboAutomatik.setItems("Ja","Nein");

                //EVENTUELL NOCH FOR-SCHLEIFE HIER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                comboKlimaanlage.addValueChangeListener((HasValue.ValueChangeListener<String>) event1 -> {

                    DataProvider<AutoEintragDTO, Void> dataProvider = suche.einfacheSuche(comboMarke.getValue(), comboModell.getValue(), comboBaujahr.getValue(),
                            suchArt, comboKlimaanlage.getValue(), comboAnzahlSitze.getValue(), comboAnzahlTuere.getValue(), comboZustand.getValue(),
                            comboKraftstoffart.getValue(), comboFarbe.getValue(), preis.getValue() == null? 0 : preis.getValue() , kilometer.getValue()== null? 0 : kilometer.getValue(),comboAutomatik.getValue());
                    grid1.setDataProvider(dataProvider);
                });

                comboAnzahlSitze.addValueChangeListener((HasValue.ValueChangeListener<String>) event1 -> {
                    DataProvider<AutoEintragDTO, Void> dataProvider = suche.einfacheSuche(comboMarke.getValue(), comboModell.getValue(), comboBaujahr.getValue(), suchArt, comboKlimaanlage.getValue(),
                            comboAnzahlSitze.getValue(), comboAnzahlTuere.getValue(), comboZustand.getValue(), comboKraftstoffart.getValue(),
                            comboFarbe.getValue(), preis.getValue() == null? 0 : preis.getValue() , kilometer.getValue()== null? 0 : kilometer.getValue(),comboAutomatik.getValue());
                    grid1.setDataProvider(dataProvider);
                });
                comboAnzahlTuere.addValueChangeListener((HasValue.ValueChangeListener<String>) event2 -> {
                    DataProvider<AutoEintragDTO, Void> dataProvider = suche.einfacheSuche(comboMarke.getValue(), comboModell.getValue(), comboBaujahr.getValue(),
                            suchArt, comboKlimaanlage.getValue(), comboAnzahlSitze.getValue(), comboAnzahlTuere.getValue(), comboZustand.getValue(),
                            comboKraftstoffart.getValue(), comboFarbe.getValue(), preis.getValue() == null? 0 : preis.getValue() , kilometer.getValue()== null? 0 : kilometer.getValue(),comboAutomatik.getValue());
                    grid1.setDataProvider(dataProvider);

                });
                comboKlimaanlage.setVisible(true);
                comboAnzahlSitze.setVisible(true);
                comboAnzahlTuere.setVisible(true);
                comboZustand.setVisible(true);
                comboKraftstoffart.setVisible(true);
                comboFarbe.setVisible(true);
                preis.setVisible(true);
                kilometer.setVisible(true);
                comboAutomatik.setVisible(true);
            } else {
                comboKlimaanlage.setVisible(false);
                comboAnzahlSitze.setVisible(false);
                comboAnzahlTuere.setVisible(false);
                comboZustand.setVisible(false);
                comboKraftstoffart.setVisible(false);
                comboFarbe.setVisible(false);
                preis.setVisible(false);
                kilometer.setVisible(false);
                comboAutomatik.setVisible(false);

                comboKlimaanlage.clear();
                comboAnzahlSitze.clear();
                comboAnzahlTuere.clear();
                suchArt = "Einfache";
            }
            });
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!




        //Layout für Margin
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();
        verticalLayout.setMargin(true);
        mainGrid.addComponent(verticalLayout,0,3,1,3);
        verticalLayout.addComponent(grid1);

        //Spalten hinzufügen
            grid1.setWidthFull();
            grid1.addColumn(AutoEintragDTO::getMarke).setCaption("Marke").setWidth(150.0);
            grid1.addColumn(AutoEintragDTO::getModell).setCaption("Modell").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getKurz_beschreibung).setCaption("Kurz Beschreibung").setWidth(400);
            grid1.addColumn(AutoEintragDTO::getBaujahr).setCaption("Baujahr").setWidth(150);
            grid1.addColumn(auto ->(auto.isKlimaanlage()? "Ja":"Nein")).setCaption("Klimaanlage").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getAnzahl_sitzplaetze).setCaption("Anzahl Sitzplaetze").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getAnzahl_tueren).setCaption("Anzahl Türen").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getZustand).setCaption("Zustand").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getKraftstoffart).setCaption("Kraftstoffart").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getAussenfarbe).setCaption("Aussenfarbe").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getKilometer).setCaption("Kilometer").setWidth(150);
            grid1.addColumn(AutoEintragDTO::getPreis).setCaption("Preis").setWidth(150);
            grid1.addColumn(auto ->(auto.isAutomatik()? "Ja":"Nein")).setCaption("Automatik").setWidth(150);


        //ValueChangeListener für Suche
        for (int j = 1; j <= 4; j++) {
            for (int i = 0; i < 3; i++) {
                ((ComboBox) searchGrid.getComponent(i + 2, j)).addValueChangeListener((HasValue.ValueChangeListener) event -> {
                    //Datenabfrage
                    DataProvider<AutoEintragDTO, Void> dataProvider = suche.einfacheSuche(comboMarke.getValue(), comboModell.getValue(), comboBaujahr.getValue(), suchArt, comboKlimaanlage.getValue(), comboAnzahlSitze.getValue(), comboAnzahlTuere.getValue(), comboZustand.getValue(), comboKraftstoffart.getValue(),
                            comboFarbe.getValue(), preis.getValue() == null ? 0 : preis.getValue(), kilometer.getValue() == null ? 0 : kilometer.getValue(), comboAutomatik.getValue());
                    grid1.setDataProvider(dataProvider);
                    grid1.setCaption("Anzahl der Ergebisse: " + suche.getRowsCount());
                    grid1.setVisible(true);

                });
            }
        }
        //Selektieren der Anzeige
        grid1.asSingleSelect().addSingleSelectionListener((SingleSelectionListener<AutoEintragDTO>) event -> {
            if(event.getValue() != null) {
                UI.getCurrent().addWindow(new AutoReservierenWindow(event.getValue()));
            }
            grid1.deselectAll();
        });


//---------------SUCHE ENDE ------------------------------------------------------------------------------------------

// baiad chck besche
            ContainerEingetrageneAutos containerEingetrageneAutos = ContainerEingetrageneAutos.getInstance();
            containerEingetrageneAutos.loadAll();
            List<AutoEintragDTO> autoListe = containerEingetrageneAutos.getListe();

            AutoTabelle<AutoEintragDTO> gAutos = new AutoTabelle<AutoEintragDTO>(autoListe);
            gAutos.setSizeFull();


        Label lPatzhalter = new Label("&nbsp", ContentMode.HTML);


        mainGrid.addComponent(topPanel, 0, 0, 1, 0);
        mainGrid.addComponent(lPatzhalter, 0, 1, 1, 1);
        mainGrid.addComponent(searchGrid, 0, 2, 1, 2);

        mainGrid.setComponentAlignment(topPanel, Alignment.TOP_CENTER);
        mainGrid.setComponentAlignment(searchGrid, Alignment.TOP_CENTER);

        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid, Alignment.TOP_CENTER);
        this.setMargin(false);
        this.addStyleName("grid");




    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


        if (UI.getCurrent().getSession().getAttribute(Roles.KUNDE) != null) {
            try {
                this.setUp();
            } catch (DatabaseException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            }
        } else if (UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER) != null) {
            UI.getCurrent().getNavigator().getCurrentNavigationState();
        } else {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
        }
    }
}

