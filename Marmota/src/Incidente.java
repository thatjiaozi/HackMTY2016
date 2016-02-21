
import java.sql.Date;
import static java.sql.Date.valueOf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osdag
 */
public class Incidente {
    
    private String sUsuario;
    private String sDescripcion;
    private int iTipo;
    private double dLatitud;
    private double dLongitud;
    private Date dteFecha;
    private int iHora;
    private int iMinutos;
    
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
