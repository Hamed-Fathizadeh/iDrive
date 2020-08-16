package org.bonn.se.model.dao;

import com.vaadin.ui.UI;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.dto.ReserviernugDTO;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservierungDAO extends AbstractDAO{

    private static ReservierungDAO instance;

    public static ReservierungDAO getInstance() {
        return instance == null ? instance = new ReservierungDAO() : instance;
    }

    public void reservieren(ReserviernugDTO reserviernugDTO) throws DatabaseException {
        String sql = "INSERT INTO idrive.tab_reservierung (kundennummer, auto_id, abholdatum, rueckgabedatum) " +
                     "VALUES (?,?,?,?)";

        PreparedStatement statement = getPreparedStatement(sql);
        try {

            Kunde kunde = (Kunde) UI.getCurrent().getSession().getAttribute(Roles.KUNDE);
            statement.setInt(1, kunde.getKundennummer());
            statement.setInt(2, reserviernugDTO.getAuto_id());
            statement.setTimestamp(3, reserviernugDTO.getAbholdatum());
            statement.setTimestamp(4, reserviernugDTO.getRueckgabedatum());

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

}
