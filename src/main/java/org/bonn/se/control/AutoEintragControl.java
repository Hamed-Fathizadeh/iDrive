package org.bonn.se.control;

import org.bonn.se.model.dao.AutoEintragDAO;
import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.services.db.exception.DatabaseException;

public class AutoEintragControl {

    private AutoEintragControl(){

    }

    public static void autoEintragen(AutoEintragDTO bewerbung) throws DatabaseException {
        AutoEintragDAO.getInstance().autoEintragen(bewerbung);
    }
}
