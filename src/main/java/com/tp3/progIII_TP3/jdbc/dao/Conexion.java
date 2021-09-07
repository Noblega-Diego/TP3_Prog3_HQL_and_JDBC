package com.tp3.progIII_TP3.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class Conexion {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATA_BASE = "ormComputer";
    private static final String SSL = "?useSSL=false";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mysql";
    
    public Connection getConnection() throws SQLException{
        Connection conexion = null;
        try {
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(URL +DATA_BASE + SSL,USER_NAME,PASSWORD);

        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el controlador jdbc");
        }
        return conexion;
    }

}
