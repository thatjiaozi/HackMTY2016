
import java.sql.Date;
import static java.sql.Date.valueOf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Incidente
 * 
 * Modela la definición de todos los objetos de tipo <code>Incidente</code>
 *
 * @author Oscar Daniel González Sosa
 * @date 21/02/16
 */
public class Incidente {
    
    private String sUsuario;  // Guarda el correo del usuario autor
    private String sDescripcion;   // Guarda la descripción del incidente
    private int iTipo;  // Marca el tipo de incidente
    /* Marcan las coordenadas de la ubicación del incidente en el mapa  */
    private double dLatitud;
    private double dLongitud;
    private Date dteFecha;   // Guarda la fecha en la que ocurrió
    private int iHora;   // Guarda la hora
    private int iMinutos;   // Guarda los minutos
    
    /**
     * Incidente
     * 
     * Método constructor utilizado para crear el objeto incidente a
     * partir de los datos proporcionados.
     * 
     * @param sUser es el <code>correo electrónico</code> del autor.
     * @param sDesc es la <code>la descripción</code> del incidente.
     * @param iT es el <code>tipo</code> de incidente.
     * @param dLat es la <code>latitud del lugar</code> del incidente.
     * @param dLong es la <code>longitud del lugar</code> del incidente.
     * @param iY es el <code>año</code> del incidente.
     * @param iM es el <code>mes</code> del incidente.
     * @param iD es el <code>día</code> del incidente.
     * @param iH es la <code>hora</code> del incidente.
     * @param iMin son los<code>minutos</code> del incidente.
     */
    public Incidente(String sUser, String sDesc, int iT, double dLat,
            double dLong, int iY, int iM, int iD, int iH, int iMin) {
        sUsuario = sUser;
        sDescripcion = sDesc;
        iTipo = iT;
        dLatitud = dLat;
        dLongitud = dLong;
        iHora = iH;
        iMinutos = iMin;
        setFecha(iY, iM, iD);
    }
    
    /**
     * Incidente
     * 
     * Método constructor utilizado para crear el objeto incidente a
     * partir de los datos proporcionados.
     * 
     * @param sUser es el <code>correo electrónico</code> del autor.
     * @param sDesc es la <code>la descripción</code> del incidente.
     * @param iT es el <code>tipo</code> de incidente.
     * @param dLat es la <code>latitud del lugar</code> del incidente.
     * @param dLong es la <code>longitud del lugar</code> del incidente.
     * @param dteDate es la <code>fecha</code> del incidente.
     * @param iH es la <code>hora</code> del incidente.
     * @param iMin son los<code>minutos</code> del incidente.
     */
    public Incidente(String sUser, String sDesc, int iT, double dLat,
            double dLong, Date dteDate, int iH, int iMin) {
        sUsuario = sUser;
        sDescripcion = sDesc;
        iTipo = iT;
        dLatitud = dLat;
        dLongitud = dLong;
        iHora = iH;
        iMinutos = iMin;
        dteFecha = dteDate;
    }
    
    public String getUsuario() {
        return sUsuario;
    }
    
    public String getDescripcion() {
        return sDescripcion;
    }
    
    public int getTipo() {
        return iTipo;
    }
    
    public double getLatitud() {
        return dLatitud;
    }
    
    public double getLongitud() {
        return dLongitud;
    }
    
    public Date getFecha() {
        return dteFecha;
    }
    
    public int getHora() {
        return iHora;
    }
    
    public int getMinutos() {
        return iMinutos;
    }
    
    public void setDescripcion(String sDesc) {
        sDescripcion = sDesc;
    }
    
    public void setTipo(int iType) {
        iTipo = iType;
    }
    
    public void setLatitud(double dLat) {
        dLatitud = dLat;
    }
    
    public void setLongitud(double dLong) {
        dLongitud = dLong;
    }
    
    public void setFecha(Date dteDate) {
        dteFecha = dteDate;
    }
    
    public void setFecha(int iY, int iM, int iD) {
        String sDate = "";
        sDate += iY;
        sDate += "-";
        sDate += iM;
        sDate += "-";
        sDate += iD;
        dteFecha = valueOf(sDate);
    }
    
    public void setHora(int iH) {
        iHora = iH;
    }
    
    public void setMinutos(int iM) {
        iMinutos = iM;
    }
}
