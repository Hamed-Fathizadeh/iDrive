package org.bonn.se.control;

import org.bonn.se.model.dao.ReservierungDAO;
import org.bonn.se.model.objects.dto.ReservierungDTO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.Timestamp;

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

    public void reservieren (ReservierungDTO reservierungDTO) {

        try {
            ReservierungDAO.getInstance().reservieren(reservierungDTO);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public boolean istReserviert (ReservierungDTO reservierungDTO) {

        try {
           return ReservierungDAO.getInstance().istReserviert(reservierungDTO);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void stornieren (ReservierungDTO reservierungDTO) {

        try {
            ReservierungDAO.getInstance().stornieren(reservierungDTO);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
