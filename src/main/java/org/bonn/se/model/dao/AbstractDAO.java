package org.bonn.se.model.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.bonn.se.services.util.db.exception.JDBCConnection;
import org.bonn.se.services.util.db.exception.DatabaseException;

public class AbstractDAO {
    protected Statement getStatement(){
        Statement statement;
        try {
            statement = JDBCConnection.getInstance().getStatement();

        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }

    protected static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);

        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }
}
