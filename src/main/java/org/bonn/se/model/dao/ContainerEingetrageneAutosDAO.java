package org.bonn.se.model.dao;

import org.bonn.se.model.objects.dto.AutoEintragDTO;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.db.exception.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContainerEingetrageneAutosDAO {

    private static ContainerEingetrageneAutosDAO instance;

    public static ContainerEingetrageneAutosDAO getInstance() {
        return instance == null ? instance = new ContainerEingetrageneAutosDAO() : instance;
    }

    public List<AutoEintragDTO> loadByPersonalnummer(int personalnummer) throws DatabaseException, SQLException {
        List<AutoEintragDTO> liste = new ArrayList<>();
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("select * from idrive.tab_auto \n" +
                    "where personalnummer =" + personalnummer

            );

            System.out.println("select * from idrive.tab_auto \n" +
                    "where personalnummer =" + personalnummer);


            while (set.next()) {


                AutoEintragDTO res = new AutoEintragDTO(set.getInt("auto_id"), set.getString("marke"), set.getString("modell")
                        , set.getString("kurz_beschreibung"), set.getString("lang_beschreibung"), set.getInt("baujahr")
                        , set.getBoolean("automatik"), set.getInt("anzahl_sitzplaetze"), set.getInt("anzahl_tueren")
                        , set.getDouble("preis_pro_tag"), set.getBoolean("klimaanlage"), set.getString("auto_typ")
                        , set.getString("autokennzeichen"), set.getInt("personalnummer")
                );
                System.out.println("conbewdao " + res.toString());
                liste.add(res);

            }


        } catch (SQLException throwables) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, throwables);
        } finally {
            assert set != null;
            set.close();
            JDBCConnection.getInstance().closeConnection();
        }
        return liste;
    }

}