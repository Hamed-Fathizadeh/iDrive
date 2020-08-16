package org.bonn.se.control;

import org.bonn.se.model.dao.AutoMarkeDAO;

import java.util.List;

public class ComponentControl {
    private static ComponentControl instance;

    public static ComponentControl getInstance() {
        if (instance == null){
            instance = new ComponentControl();
        }
        return instance ;
    }

    public List<String> getAutoMarke () {

        return AutoMarkeDAO.getInstance().getAutoMarke();
    }

    public List<String> getAutoModell (String marke) {

        return AutoMarkeDAO.getInstance().getAutoModell(marke);
    }
}
