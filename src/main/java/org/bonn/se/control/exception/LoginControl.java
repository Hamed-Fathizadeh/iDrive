package org.bonn.se.control.exception;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.entitites.User;
import org.bonn.se.services.util.db.exception.DatabaseException;
import org.bonn.se.services.util.db.exception.JDBCConnection;
import org.bonn.se.services.util.Views;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {
    private LoginControl(){

    }

    private static LoginControl instance;

    public static LoginControl getInstance() {
        if (instance == null){
            instance = new LoginControl();
        }
        return instance;
    }


}
