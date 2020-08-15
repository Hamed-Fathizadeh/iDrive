package org.bonn.se.model.objects.entitites;

import org.bonn.se.model.dao.ContainerEingetrageneAutosDAO;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerEingetrageneAutos {

    private List<AutoEintragDTO> liste;

    private static ContainerEingetrageneAutos instance;

    public static ContainerEingetrageneAutos getInstance() {
        return instance == null ? instance = new ContainerEingetrageneAutos() : instance;
    }

    public int getAnzahl(){
        return liste.size();
    }

    public void loadByPersonalnummer(int personalnummer){
        try {
            liste = ContainerEingetrageneAutosDAO.getInstance().loadByPersonalnummer(personalnummer);
        }
        catch(DatabaseException | SQLException throwables){
            Logger.getLogger(ContainerEingetrageneAutosDAO.class.getName()).log(Level.SEVERE, null, throwables);
        }

    }

    public List<AutoEintragDTO> getListe(){
        return liste;
    }

}
