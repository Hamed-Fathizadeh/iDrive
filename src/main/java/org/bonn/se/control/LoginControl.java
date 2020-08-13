package org.bonn.se.control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.bonn.se.control.NoSuchUserOrPassword;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.entitites.Kunde;
import org.bonn.se.model.objects.entitites.User;
import org.bonn.se.model.objects.entitites.Vertriebler;
import org.bonn.se.services.db.exception.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.util.Views;
import org.bonn.se.services.util.Roles;



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
    public void checkAuthentication ( String login , String password) throws NoSuchUserOrPassword, DatabaseException, SQLException {

        ResultSet set;
        Statement statement = null;

        String c = "'";
        if(password.indexOf('/') != -1 || password.indexOf(c.charAt(0)) != -1){
            throw new DatabaseException("Die Zeichen ' oder / sind nicht als Passwort erlaubt!.");
        }

        try {
            statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM iDrive.tab_user "
                    + "WHERE upper(iDrive.tab_user.email) = '" +login.toUpperCase() + "'"
                    + " AND iDrive.tab_user.passwort = '" + password + "'");

        } catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE,null,throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        User user;
        try {
            if ( set.next()) {

                user = new User();
                user.setVorname(set.getString("vorname"));
                user.setNachname(set.getString("nachname"));
                user.setEmail((set.getString(1)));
                user.setPasswort(set.getString(2));
                user.setType(set.getString(5));

                if(set.getString(5).equals("V")) {
                    Vertriebler vertriebler = new Vertriebler();
                    MyUI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER,vertriebler);
                    vertriebler.setEmail(user.getEmail());
                    vertriebler.setVorname(user.getVorname());
                    vertriebler.setNachname(user.getNachname());
                    //vertriebler = ProfilDAO.getInstance().getUnternehmenProfil(vertriebler);

                    UI.getCurrent().getSession().setAttribute(Roles.VERTRIEBLER,vertriebler);

                } else if(set.getString(5).equals("E")) {
                    Kunde endkunde;
                    //endkunde = ProfilDAO.getInstance().getStudent(user.getEmail());

                    // UI.getCurrent().getSession().setAttribute(Roles.Endkunde, endkunde);
                }
            }else{
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }

        UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
    }

    public static void logoutUser() {

        VaadinSession vaadinSession = UI.getCurrent().getSession();
        vaadinSession.setAttribute(Roles.KUNDE,null);
        vaadinSession.setAttribute(Roles.VERTRIEBLER,null);
        UI.getCurrent().getNavigator().navigateTo(Views.LOGINVIEW);
    }


}
