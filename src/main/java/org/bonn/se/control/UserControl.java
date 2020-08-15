package org.bonn.se.control;


import org.bonn.se.model.dao.UserDAO;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserControl {


    private static UserControl instance;

    public static UserControl getInstance() {
        if (instance == null){
            instance = new UserControl();
        }
        return instance;
    }
    public boolean existUser(String email )  {
        try {
            return UserDAO.getInstance().getUserbyEmail(email);
        } catch (DatabaseException | SQLException e) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public int getPersonalnummer(String email )  {
        try {
            return UserDAO.getInstance().getPersonalnummer(email);
        } catch (DatabaseException | SQLException e) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }
}
