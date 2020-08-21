package org.bonn.se.model.dao;

import com.vaadin.ui.UI;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.dto.ReservierungDTO;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservierungDAO extends AbstractDAO{

    private static ReservierungDAO instance;

    public static ReservierungDAO getInstance() {
        return instance == null ? instance = new ReservierungDAO() : instance;
    }

    public void reservieren(ReservierungDTO reservierungDTO) throws DatabaseException {
        String sql = "INSERT INTO idrive.tab_reservierung (kundennummer, auto_id, abholdatum, rueckgabedatum) " +
                     "VALUES (?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(sql);
        try {

            Kunde kunde = (Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE);
            statement.setInt(1, kunde.getKundennummer());
            statement.setInt(2, reservierungDTO.getAuto_id());
            statement.setTimestamp(3, reservierungDTO.getAbholdatum());
            statement.setTimestamp(4, reservierungDTO.getRueckgabedatum());

            statement.executeUpdate();
            org.bonn.se.gui.window.ConfirmationWindow confWindow =  new org.bonn.se.gui.window.ConfirmationWindow("Auto wurde reserviert!");
            UI.getCurrent().addWindow(confWindow);
            UI.getCurrent().getNavigator().navigateTo(Views.KUNDEHOMEVIEW);

        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }


    }

    public Timestamp[] istReserviert(ReservierungDTO reservierungDTO) throws DatabaseException {
        ResultSet set = null;
        boolean res = false;
        Timestamp[] datumReserviert = new Timestamp[2];
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("select * \n" +
                    "  from idrive.tab_reservierung\n" +
                    " where '"+reservierungDTO.getAbholdatum()+"'  between abholdatum and rueckgabedatum\n" +
                    "    or '"+reservierungDTO.getRueckgabedatum()+"'    between abholdatum and rueckgabedatum\n"+
                    "    and auto_id = "+reservierungDTO.getAuto_id()


            );

            if (set.next()) {
                datumReserviert[0] =  set.getTimestamp("abholdatum");
                datumReserviert[1] =  set.getTimestamp("rueckgabedatum");
            }

          } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
          } finally {
            assert set != null;
            JDBCConnection.getInstance().closeConnection();
        }
        return datumReserviert;

    }


    public List<ReservierungDTO> loadByKundennummer(int kundennummer) throws DatabaseException, SQLException {
        List<ReservierungDTO> liste = new ArrayList<>();
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("Select a.auto_id, a.marke, a.modell, a.kurz_beschreibung, a.kurz_beschreibung, a.lang_beschreibung, a.baujahr, a.automatik, a.anzahl_sitzplaetze, a.anzahl_tueren,\n" +
                    "       a.preis_pro_tag, a.klimaanlage, a.auto_typ, a.autokennzeichen, r.abholdatum , r.rueckgabedatum \n" +
                    "  from idrive.tab_auto a\n" +
                    "  join idrive.tab_reservierung r\n" +
                    "    on a.auto_id = r.auto_id\n" +
                    " where r.kundennummer = " + kundennummer

            );



            while (set.next()) {


                ReservierungDTO res = new ReservierungDTO(kundennummer, set.getInt("auto_id"), set.getString("marke"),set.getString("modell")
                        , set.getString("kurz_beschreibung"), set.getString("lang_beschreibung"), set.getInt("baujahr")
                        , set.getBoolean("automatik"), set.getInt("anzahl_sitzplaetze"), set.getInt("anzahl_tueren")
                        , set.getDouble("preis_pro_tag"), set.getBoolean("klimaanlage"), set.getString("auto_typ")
                        , set.getString("autokennzeichen"), set.getTimestamp("abholdatum"),set.getTimestamp("rueckgabedatum")
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


    public void stornieren(ReservierungDTO reservierungDTO) throws DatabaseException {
        String sql = "DELETE FROM idrive.tab_reservierung WHERE kundennummer = "+reservierungDTO.getKundennummer() +" and auto_id = " +
                ""+reservierungDTO.getAuto_id()+ " and abholdatum = '"+reservierungDTO.getAbholdatum() +"' and rueckgabedatum = '"+reservierungDTO.getRueckgabedatum()+"'";

        PreparedStatement statement = getPreparedStatement(sql);
        try {
            assert statement != null;
            statement.executeUpdate();
        }catch(NullPointerException | SQLException e){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }

}
