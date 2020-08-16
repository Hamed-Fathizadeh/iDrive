package org.bonn.se.model.dao;

import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMarkeDAO extends AbstractDAO {

    private static AutoMarkeDAO instance;

    public static AutoMarkeDAO getInstance() {
        return instance == null ? instance = new AutoMarkeDAO() : instance;
    }

    public List<String> getAutoMarke() {
        ResultSet set = null;
        List<String> autoMarkeList = new ArrayList<>();

        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("Select distinct marke from idrive.tab_marke_modell order by 1");
            while (true) {
                assert set != null;
                if (!set.next()) break;

                autoMarkeList.add(set.getString(1));
            }

        }catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try {
                set.close();
            } catch (SQLException throwables) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        return autoMarkeList;
    }

    public List<String> getAutoModell(String marke) {
        ResultSet set = null;
        List<String> autoMarkeList = new ArrayList<>();

        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("Select  modell from idrive.tab_marke_modell where marke = '"+marke+"'order by modell");
            while (true) {
                assert set != null;
                if (!set.next()) break;

                autoMarkeList.add(set.getString(1));
            }

        }catch (SQLException | DatabaseException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        }finally {
            assert set != null;
            try {
                set.close();
            } catch (SQLException throwables) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,throwables);
            }
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        return autoMarkeList;
    }
}
