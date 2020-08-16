package org.bonn.se.model.dao;

public class ProfilDAO extends AbstractDAO {

    private static ProfilDAO instance;

    public static ProfilDAO getInstance() {
        return instance == null ? instance = new ProfilDAO() : instance;
    }
}
