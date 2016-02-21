
import static java.lang.System.currentTimeMillis;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Database
 * 
 * Maneja y lleva a cabo todas las operaciones relscionadas con el manejo de
 * la base de datos en MySQL Server.
 * 
 * @author Oscar Daniel González Sosa
 * @date 21/02/16
 */
public class Database {
    
    private String sUrl; // Guarda el url de la base de datos
    private String sUser; // Guarda el usuario empleado para acceder a la DB
    private String sPass; // Guarda la contraseña de dicho usuario
    private Connection conConnection; // Maneja la conexión con la DB
    
    /**
     * Database
     * 
     * Método constructor utilizado para crear el objeto Database ligado a la
     * base de datos de MySQL Server.
     * 
     */
    public Database () {
        sUrl = "jdbc:mysql://10.12.175.205:3306/Marmota";
        sUser = "prueba";
        sPass = "123";
        connect();       
    }
    
    /**
     * Connect
     * 
     * Método que se encarga de establecer la conexión entre el objeto y la DB.
     * 
     */
    public void connect() {
        try {
            conConnection = DriverManager.getConnection(sUrl, sUser, sPass);
            
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    
    /**
     * Login
     * 
     * Método que recibe un string con un correo y contraseña separados por un
     * caracter '#' y busca su respectiva entrada en la base de datos; en caso
     * de encontrarla, da al usuario acceso a la aplicación.
     * 
     * @param sInfo es el <code>correo y contraseña</code> del usuario.
     * @return bLogin indica si se inició sesión exitosamente.
     */
    public boolean login(String sInfo) {
        // Buscar la casilla del símbolo #
        int iGato = sInfo.indexOf('#');
        /* Extrar el correo y la contraseña del string */
        String sCorreo = sInfo.substring(0, iGato);
        String sContra = sInfo.substring(iGato + 1);
        PreparedStatement stmStatement;
        try {
            // Armar el query de pregunta a la DB
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = '";
            sQuery+=sCorreo;
            sQuery +="' AND Password = '";
            sQuery+=sContra;
            sQuery+="';";
            stmStatement = conConnection.prepareStatement(sQuery);
            try {
                // Evaluar si se encontró una entrada
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
    
    /**
     * Register
     * 
     * Método que recibe un string con un correo y contraseña separados por un
     * caracter '#' y busca su respectiva entrada en la base de datos; en caso
     * de no encontrar un usuario registrado con el mismo correo, lo agrega a
     * la tabla.
     * 
     * @param sInfo es el <code>correo y contraseña</code> del usuario.
     * @return bRegister indica si se registró al usuario exitosamente.
     */
    public boolean register(String sInfo) {
        // Buscar la casilla del símbolo #
        int iGato = sInfo.indexOf('#');
        /* Extrar el correo y la contraseña del string */
        String sCorreo = sInfo.substring(0, iGato);
        String sContra = sInfo.substring(iGato + 1);
        PreparedStatement stmStatement;
        try {
            // Armar el query de pregunta a la DB
            String sQuery = "SELECT * FROM Usuarios WHERE Correo = '";
            sQuery+=sCorreo;
            sQuery+="';";
            stmStatement = conConnection.prepareStatement(sQuery);
            try {
                // Evaluar si se encontró una entrada
                ResultSet rsReply = stmStatement.executeQuery(sQuery);
                if (rsReply.absolute(1)) {
                    return false;
                }
                // Si no se encontró, agregar una nueva entrada a la tabla
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
    
    /**
     * Incidente
     * 
     * Método que da de alta un incidente en la base de datos.
     * 
     * @param idtPrueba es el <code>incidente</code> a dar de alta.
     * @return bIncidente indica si se guardó el incidente exitosamente.
     */
    public boolean incidente(Incidente idtPrueba) {
        PreparedStatement stmStatement;
        try {
            // Armar el query de INSERT con los datos del incidente
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
            // Agregar el incidente a la tabla
            stmStatement.executeUpdate(sQuery);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
    public List<Incidente> filtrarIncidenteHora(double dLat, double dLong,
            int iHora1, int iMinutos1, int iHora2, int iMinutos2) {
        List<Incidente> arrIncidentes = new ArrayList<Incidente>();
        PreparedStatement stmStatement;
        try {
            String sQuery = "SELECT * FROM Incidentes WHERE (Hora = ";
            sQuery += iHora1;
            sQuery += " AND Minutos >= ";
            sQuery += iMinutos1;
            if (iHora1 == iHora2) {
                sQuery += " AND Minutos <= ";
                sQuery += iMinutos2;
            }
            else if (iHora1 < iHora2) {
                sQuery += " OR Hora > ";
                sQuery += iHora1;
                sQuery += " AND Hora < ";
                sQuery += iHora2;
                sQuery += " OR Hora = ";
                sQuery += iHora2;
                sQuery += " AND Minutos <= ";
                sQuery += iMinutos2;
            }
            else {
                sQuery += " OR Hora > ";
                sQuery += iHora1;
                sQuery += " OR Hora < ";
                sQuery += iHora2;
                sQuery += " OR Hora = ";
                sQuery += iHora2;
                sQuery += " AND Minutos <= ";
                sQuery += iMinutos2;
            }
            sQuery += ") AND Latitud >= ";
            sQuery += (dLat - .0012);
            sQuery += " AND Latitud < ";
            sQuery += (dLat + .0012);
            sQuery += " AND Longitud >= ";
            sQuery += (dLong - .0012);
            sQuery += " AND Longitud < ";
            sQuery += (dLong + .0012);
            sQuery += ";";
            stmStatement = conConnection.prepareStatement(sQuery);
            ResultSet rsReply = stmStatement.executeQuery(sQuery);
            while (rsReply.next()) {
                Incidente idtCapturado = new Incidente(rsReply.getString("Usuario"),
                        rsReply.getString("Descripcion"), rsReply.getInt("Tipo"),
                        rsReply.getDouble("Latitud"), rsReply.getDouble("Longitud"),
                        valueOf(rsReply.getString("Fecha")), rsReply.getInt("Hora"),
                        rsReply.getInt("Minutos"));
                arrIncidentes.add(idtCapturado);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return arrIncidentes;
    }
    
    public boolean panicIncidentes() {
        return true;
    }
}