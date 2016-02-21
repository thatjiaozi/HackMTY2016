
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        sUrl = "jdbc:mysql://10.12.175.205:3306/Marmota";
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
        int iGato = sInfo.indexOf('#');
        String sCorreo = sInfo.substring(0, iGato - 1);
        String sContra = sInfo.substring(iGato + 1);
        PreparedStatement stmStatement;
        try {
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = ? AND Password = ?";
            stmStatement = conConnection.prepareStatement(sQuery);
            stmStatement.setString(1, sCorreo);
            stmStatement.setString(2, sContra);
            try {
                ResultSet rsReply = stmStatement.executeQuery(sQuery);
                return (rsReply.absolute(1));
            } catch (SQLException ex) {
                throw new IllegalStateException("Cannot connect the database!", ex);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!", ex);
        }
        
    }

