package org.bonn.se.model.dao;

import com.vaadin.ui.UI;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;
import org.bonn.se.gui.window.ConfirmationWindow;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoEintragDAO  extends AbstractDAO{

    private static AutoEintragDAO instance;

    public static AutoEintragDAO getInstance() {
        return instance == null ? instance = new AutoEintragDAO() : instance;
    }

    public void autoEintragen(AutoEintragDTO autoEintragDTO) throws DatabaseException {
        String sql = "INSERT INTO idrive.tab_auto (marke,modell,kurz_beschreibung,lang_beschreibung,baujahr,automatik,anzahl_sitzplaetze," +
                     "                             anzahl_tueren,preis_pro_tag,klimaanlage,auto_typ,autokennzeichen,personalnummer)"+
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(sql);
        try {

            statement.setString(1,autoEintragDTO.getMarke());
            statement.setString(2, autoEintragDTO.getModell());
            statement.setString(3, autoEintragDTO.getKurz_beschreibung());
            statement.setString(4, autoEintragDTO.getLang_beschreibung());
            statement.setInt(5, autoEintragDTO.getBaujahr());
            statement.setBoolean(6, autoEintragDTO.isAutomatik());
            statement.setInt(7, autoEintragDTO.getAnzahl_sitzplaetze());
            statement.setInt(8, autoEintragDTO.getAnzahl_tueren());
            statement.setDouble(9, autoEintragDTO.getPreis_pro_tag());
            statement.setBoolean(10, autoEintragDTO.isKlimaanlage());
            statement.setString(11, autoEintragDTO.getAuto_type());
            statement.setString(12, autoEintragDTO.getAutokennzeichen());
            Vertriebler vertriebler = (Vertriebler) UI.getCurrent().getSession().getAttribute(Roles.VERTRIEBLER);
            statement.setInt(13, vertriebler.getPersonalnummer());

            statement.executeUpdate();
            ConfirmationWindow confWindow =  new ConfirmationWindow("Auto wurde eingetragen!");
            UI.getCurrent().addWindow(confWindow);
            UI.getCurrent().getNavigator().navigateTo(Views.VERTIEBLERHOMEVIEW);

        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }


    }

    public void autoEintragenLoeschen(AutoEintragDTO autoEintragDTO) throws DatabaseException {

        String sql = "DELETE FROM idrive.tab_auto WHERE auto_id = "+autoEintragDTO.getAuto_id();
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
