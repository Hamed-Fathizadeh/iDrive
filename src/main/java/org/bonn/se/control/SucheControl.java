package org.bonn.se.control;

import com.vaadin.data.provider.DataProvider;

import org.bonn.se.model.dao.ContainerEingetrageneAutosDAO;
import org.bonn.se.model.objects.dto.AutoEintragDTO;

import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SucheControl  implements Suche {


    private static SucheControl instance;
    List<AutoEintragDTO> autoEintragDTO = null;

    public static SucheControl getInstance() {
        if (instance == null){
            instance = new SucheControl();
        }
        return instance ;
    }

    static int rowsCount;


    @Override
    public int getRowsCount() {
        return rowsCount;
    }

    @Override
    public DataProvider<AutoEintragDTO,Void> einfacheSuche(String comboMarke, String comboModell,
                                                           String comboBaujahr, String suchArt, String comboKlimaanlage,String comboAnzahlSitze, String comboAnzahlTuere,
                                                           String comboZustand, String comboKraftstoffart, String comboFarbe, int preis, int kilometer, String comboAutomatik) {
        try {
            autoEintragDTO = ContainerEingetrageneAutosDAO.getInstance().loadSuche( comboMarke,  comboModell,  comboBaujahr,  suchArt,  comboKlimaanlage, comboAnzahlSitze,  comboAnzahlTuere,
                                                                                    comboZustand,  comboKraftstoffart,  comboFarbe,  preis,  kilometer,comboAutomatik);
        } catch (DatabaseException | SQLException e) {
            Logger.getLogger(SucheControl.class.getName()).log(Level.SEVERE,null,e);
        }
        rowsCount = autoEintragDTO.size();

        return DataProvider.fromCallbacks(query -> {
            int offset = query.getOffset();
            int limit = query.getLimit();
            return autoEintragDTO.stream().skip(offset).limit(limit);
        } ,query ->getRowsCount() );

    }
}
