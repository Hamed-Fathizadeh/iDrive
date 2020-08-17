package org.bonn.se.model.objects.entitites;

import org.bonn.se.model.dao.ContainerEingetrageneAutosDAO;
import org.bonn.se.model.dao.ReservierungDAO;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.model.objects.dto.ReservierungDTO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerReservierungen {
    private List<ReservierungDTO> liste;

    private static ContainerReservierungen instance;

    public static ContainerReservierungen getInstance() {
        return instance == null ? instance = new ContainerReservierungen() : instance;
    }

    public int getAnzahl(){
        return liste.size();
    }

    public void loadByKundennummer(int kundennummer){
        try {
            liste = ReservierungDAO.getInstance().loadByKundennummer(kundennummer);
        }
        catch(DatabaseException | SQLException throwables){
            Logger.getLogger(ContainerEingetrageneAutosDAO.class.getName()).log(Level.SEVERE, null, throwables);
        }

    }

    public List<ReservierungDTO> getListe(){
        return liste;
    }
}
