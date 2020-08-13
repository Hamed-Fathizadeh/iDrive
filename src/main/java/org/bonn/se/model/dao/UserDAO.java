package org.bonn.se.model.dao;


import org.bonn.se.model.objects.entitites.User;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO  extends AbstractDAO {

    private static UserDAO instance;

    public static UserDAO getInstance() {
        return instance == null ? instance = new UserDAO() : instance;
    }


    public User getUser(String email) throws DatabaseException, SQLException {

        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT * "
                    + "FROM lacasa.tab_user "
                    + "WHERE upper(lacasa.tab_user.email) = '" + email.toUpperCase() + "'");

            User user = null;

            while (set.next()) {

                user = new User();
                user.setEmail(set.getString(1));
                user.setPasswort(set.getString(2));
                user.setVorname(set.getString(3));
                user.setNachname(set.getString(4));
                user.setType(set.getString(5));
            }

            return user;
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return null;

    }

    public void registerUser(User user) throws DatabaseException {
        String sql;

        if (user.getType().equals("S")) {
            sql = "INSERT INTO lacasa.tab_user VALUES(?,?,?,?,?); INSERT INTO lacasa.tab_student (email) VALUES(?);";
        } else{
            sql = "INSERT INTO lacasa.tab_user VALUES(?,?,?,?,?); INSERT INTO lacasa.tab_unternehmen (firmenname,hauptsitz,bundesland,email) VALUES(?,?,?,?);";
        }
        PreparedStatement statement = AbstractDAO.getPreparedStatement(sql);


        try {
            assert statement != null;
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswort());
            statement.setString(3, user.getVorname());
            statement.setString(4, user.getNachname());
            statement.setString(5, user.getType());
            if(user.getType().equals("C")) {


                statement.setString(9, user.getEmail());
            } else {
                statement.setString(6, user.getEmail());
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }

    public boolean getUserbyEmail(String email) throws DatabaseException, SQLException {

        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT * "
                    + "FROM tab_user "
                    + "WHERE upper(tab_user.email) = '" + email.toUpperCase() + "'");

            while (set.next()) {
                return set.getString(1).equalsIgnoreCase(email);
            }
        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return false;
    }
}