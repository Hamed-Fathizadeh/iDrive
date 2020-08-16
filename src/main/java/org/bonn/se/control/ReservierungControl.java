package org.bonn.se.control;

import org.bonn.se.model.dao.AutoMarkeDAO;
import org.bonn.se.model.dao.ReservierungDAO;
import org.bonn.se.model.objects.dto.ReserviernugDTO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.util.List;

public class ReservierungControl {

    private ReservierungControl(){

    }

    private static ReservierungControl instance;

    public static ReservierungControl getInstance() {
        if (instance == null){
            instance = new ReservierungControl();
        }
        return instance;
    }

    public void reservieren (ReserviernugDTO reserviernugDTO) {

        try {
            ReservierungDAO.getInstance().reservieren(reserviernugDTO);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
