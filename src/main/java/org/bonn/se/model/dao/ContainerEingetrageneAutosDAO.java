package org.bonn.se.model.dao;

import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerEingetrageneAutosDAO {

    private static ContainerEingetrageneAutosDAO instance;

    public static ContainerEingetrageneAutosDAO getInstance() {
        return instance == null ? instance = new ContainerEingetrageneAutosDAO() : instance;
    }

    public List<AutoEintragDTO> loadByPersonalnummer(int personalnummer) throws DatabaseException, SQLException {
        List<AutoEintragDTO> liste = new ArrayList<>();
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("select * from idrive.tab_auto \n" +
                    "where personalnummer =" + personalnummer

            );



            while (set.next()) {


                AutoEintragDTO res = new AutoEintragDTO(set.getInt("auto_id"), set.getString("marke"), set.getString("modell")
                        , set.getString("kurz_beschreibung"), set.getString("lang_beschreibung"), set.getInt("baujahr")
                        , set.getBoolean("automatik"), set.getInt("anzahl_sitzplaetze"), set.getInt("anzahl_tueren")
                        , set.getInt("preis"), set.getBoolean("klimaanlage"), set.getString("auto_typ")
                        , set.getString("zustand"), set.getInt("kilometer"),set.getString("kraftstoffart"),
                        set.getString("aussenfarbe"),set.getInt("personalnummer")
                );

                liste.add(res);

            }


        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return liste;
    }

    public List<AutoEintragDTO> loadSuche(String comboMarke, String comboModell, String comboBaujahr,
                                          String suchArt, String comboKlimaanlage, String comboAnzahlSitze, String comboAnzahlTuere,
                                          String comboZustand, String comboKraftstoffart, String comboFarbe, int preis, int kilometer,String comboAutomatik) throws DatabaseException, SQLException {
        List<AutoEintragDTO> liste = new ArrayList<>();
        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();
        System.out.println("comboModell: "+comboModell);
        try {


            StringBuilder sbMarke = new StringBuilder(comboMarke == null ? " " : " and marke = '" + comboMarke + "' ");
            StringBuilder sModell = new StringBuilder(comboModell == null ? " " : " and modell = '" + comboModell + "' ");
            StringBuilder sbBaujahr = new StringBuilder(comboBaujahr == null ? " " : " and baujahr >= " + Integer.parseInt(comboBaujahr) + " ");
            StringBuilder sbKlimaanlage = new StringBuilder(" ");
            StringBuilder sbAnzahlTueren = new StringBuilder(" ");
            StringBuilder sbAnzahlSitze = new StringBuilder(" ");
            StringBuilder sZustand = new StringBuilder(" ");
            StringBuilder sKraftstoffart = new StringBuilder(" ");
            StringBuilder sFarbe = new StringBuilder(" ");
            StringBuilder sPreis = new StringBuilder(" ");
            StringBuilder sKilometer = new StringBuilder(" ");
            StringBuilder sAutomatik = new StringBuilder(" ");


            if(suchArt.equals("Erweitert")){
                 sbKlimaanlage = new StringBuilder(comboKlimaanlage == null ? " " : comboKlimaanlage.equals("Ja")? " and klimaanlage  = true ": " and klimaanlage  = false "  );
                 sbAnzahlTueren = new StringBuilder(comboAnzahlTuere == null ? " " : " and anzahl_tueren >= " + Integer.parseInt(comboAnzahlTuere) + " ");
                 sbAnzahlSitze = new StringBuilder(comboAnzahlSitze == null ? " " : " and anzahl_sitzplaetze >= " + Integer.parseInt(comboAnzahlSitze) + " ");

                 sZustand = new StringBuilder(comboZustand == null ? " " : " and zustand = '" + comboZustand + "' ");
                 sKraftstoffart = new StringBuilder(comboKraftstoffart == null ? " " : " and kraftstoffart = '" + comboKraftstoffart + "' ");
                 sFarbe = new StringBuilder(comboFarbe == null ? " " : " and aussenfarbe = '" + comboFarbe + "' ");
                 sPreis = new StringBuilder(preis == 0 ? " " : " and preis <= " + preis + " ");
                 sKilometer = new StringBuilder(kilometer == 0 ? " " : " and kilometer <= " + kilometer + " ");
                 sAutomatik = new StringBuilder(comboAutomatik == null ? " " : comboAutomatik.equals("Ja")? " and automatik  = true ": " and automatik  = false "  );
            }


            System.out.println("select * from idrive.tab_auto"+
                    " where 1 = 1 "+ sbMarke  + sModell + sbBaujahr + sbKlimaanlage +sbAnzahlTueren+sbAnzahlSitze+sZustand+sKraftstoffart+sFarbe+sPreis+sKilometer+sAutomatik);

            set = statement.executeQuery("select * from idrive.tab_auto"+
                                             " where 1 = 1 "+ sbMarke  + sModell + sbBaujahr + sbKlimaanlage +sbAnzahlTueren+sbAnzahlSitze +sZustand+sKraftstoffart+sFarbe+sPreis+sKilometer+sAutomatik);


            while (set.next()) {
                AutoEintragDTO res = new AutoEintragDTO(set.getInt("auto_id"), set.getString("marke"), set.getString("modell")
                        , set.getString("kurz_beschreibung"), set.getString("lang_beschreibung"), set.getInt("baujahr")
                        , set.getBoolean("automatik"), set.getInt("anzahl_sitzplaetze"), set.getInt("anzahl_tueren")
                        , set.getInt("preis"), set.getBoolean("klimaanlage"), set.getString("auto_typ")
                        , set.getString("zustand"), set.getInt("kilometer"),set.getString("kraftstoffart"),
                          set.getString("aussenfarbe"),set.getInt("personalnummer")
                );

                liste.add(res);
            }


        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return liste;
    }


    public List<AutoEintragDTO> loadAll() throws DatabaseException, SQLException {
        List<AutoEintragDTO> liste = new ArrayList<>();
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("select * from idrive.tab_auto "

            );


            while (set.next()) {


                AutoEintragDTO res = new AutoEintragDTO(set.getInt("auto_id"), set.getString("marke"), set.getString("modell")
                        , set.getString("kurz_beschreibung"), set.getString("lang_beschreibung"), set.getInt("baujahr")
                        , set.getBoolean("automatik"), set.getInt("anzahl_sitzplaetze"), set.getInt("anzahl_tueren")
                        , set.getInt("preis"), set.getBoolean("klimaanlage"), set.getString("auto_typ")
                        , set.getString("zustand"), set.getInt("kilometer"),set.getString("kraftstoffart"),
                        set.getString("aussenfarbe"),set.getInt("personalnummer")
                );
                liste.add(res);

            }


        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return liste;
    }

}