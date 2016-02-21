
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osdag
 */
public class Database {
    
    private String sUrl;
    private String sUser;
    private String sPass;
    private Connection conConnection;
    
    public Database () {
        sUrl = "jdbc:mysql://127.0.0.1:3306/Marmota";
        sUser = "prueba";
        sPass = "123";
        connect();
        String sInfo = "blablabla#blablabla";
        System.out.println(login(sInfo));
    }
    
    public void connect() {
        try (Connection conPrueba = DriverManager.getConnection(sUrl, sUser, sPass)) {
            System.out.println("Database connected!");
            conConnection = conPrueba;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    
    public boolean login(String sInfo) {
        System.out.println("login");
        int iGato = sInfo.indexOf('#');
        String sCorreo = sInfo.substring(0, iGato - 1);
        String sContra = sInfo.substring(iGato + 1);
        Statement stmStatement;
        try {
            stmStatement = conConnection.createStatement();
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = '" + sCorreo
                    + "' AND Password = '" + sContra + "'";
            try {
                ResultSet rsReply = stmStatement.executeQuery(sQuery);
                rsReply.absolute(1);
                return (rsReply.getNString("Password").equals(sContra)) && (sCorreo == rsReply.getNString("Correo"));
            } catch (SQLException ex) {
                throw new IllegalStateException("Cannot connect the database!", ex);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }
        
    }

    
}
