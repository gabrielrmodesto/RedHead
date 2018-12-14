/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielmodesto
 */
public class ConnectionFactory {
private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/REDHEAD";
    private static final String USER = "gabriel";
    private static final String PASS = "root";
    
     //METODO PARA CONECTAR O BANCO
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("erro na conexo: ", ex);
        }
    }
    
     public static void closeConection(Connection com) {

        try {
            if (com != null) {
                com.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public static void closeConection(Connection com, PreparedStatement stmt) {
        closeConection(com);
        try {
            if(stmt != null){
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static void closeConection(Connection com, PreparedStatement stmt, ResultSet rs) {
        closeConection(com, stmt);
        try {
            if(rs != null){
            rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
