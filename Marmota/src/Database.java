
import static java.lang.System.currentTimeMillis;
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
        
        
        
    }
    
    public void connect() {
        try {
            conConnection = DriverManager.getConnection(sUrl, sUser, sPass);
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    
    public boolean login(String sInfo) {
        int iGato = sInfo.indexOf('#');
        String sCorreo = sInfo.substring(0, iGato);
        String sContra = sInfo.substring(iGato + 1);
        PreparedStatement stmStatement;
        try {
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = '";
            sQuery+=sCorreo;
            sQuery +="' AND Password = '";
            sQuery+=sContra;
            sQuery+="';";
            stmStatement = conConnection.prepareStatement(sQuery);
            try {
                ResultSet rsReply = stmStatement.executeQuery(sQuery);
                return (rsReply.absolute(1));
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean register(String sInfo) {
        int iGato = sInfo.indexOf('#');
        String sCorreo = sInfo.substring(0, iGato);
        String sContra = sInfo.substring(iGato + 1);
        PreparedStatement stmStatement;
        try {
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = '";
            sQuery+=sCorreo;
            sQuery+="';";
            stmStatement = conConnection.prepareStatement(sQuery);
            try {
                ResultSet rsReply = stmStatement.executeQuery(sQuery);
                if (rsReply.absolute(1)) {
                    return false;
                }
                sQuery = "INSERT INTO Usuarios VALUES ('";
                sQuery += sCorreo;
                sQuery += "', '";
                sQuery += sContra;
                sQuery += "')";
                stmStatement.executeUpdate(sQuery);
                return true;
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (SQLException ex) {  
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean incidente(String sUsuario, String sDescripcion, int iTipo,
            double dLatitud, double dLongitud, int iDia, int iMes, int iYear,
            int iHora, int iMinutos) {
        PreparedStatement stmStatement;
        Incidente idtPrueba = new Incidente(sUsuario, sDescripcion, iTipo,
                dLatitud, dLongitud, iYear, iMes, iDia, iHora, iMinutos);
        try {
            String sQuery = "INSERT INTO Incidentes VALUES('";
            sQuery += idtPrueba.getUsuario();
            sQuery += currentTimeMillis();
            sQuery += "', " + idtPrueba.getTipo() + ", ";
            sQuery += idtPrueba.getLatitud() + ", " + idtPrueba.getLongitud();
            sQuery += ", '";
            sQuery += idtPrueba.getDescripcion();
            sQuery += "', '" + idtPrueba.getFecha().toString() + "', ";
            sQuery += idtPrueba.getHora() + ", " + idtPrueba.getMinutos();
            sQuery += ", '";
            sQuery += idtPrueba.getUsuario();
            sQuery += "')";
            stmStatement = conConnection.prepareStatement(sQuery);
            stmStatement.executeUpdate(sQuery);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean filtrarIncidenteHora(int iHora1, int iMinutos1, int iHora2,
            int iMinutos2) {
        PreparedStatement stmStatement;
        try {
            String sQuery = "SELECT * FROM Incidentes Hora = ";
            sQuery += iHora1;
            sQuery += " AND Minutos >= ";
            sQuery += iMinutos1;
            if (iHora1 == iHora2) {
                sQuery += " AND Minutos < ";
                sQuery += iMinutos2;
            }
            sQuery += ";";
            stmStatement = conConnection.prepareStatement(sQuery);
            ResultSet rsReply = stmStatement.executeQuery(sQuery);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
        
    }
}