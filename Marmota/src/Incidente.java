
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
    
    private Incidente(String sUser, String sDesc, int iT, double dLat,
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
    
    private String getUsuario() {
        return sUsuario;
    }
    
    private String getDescripcion() {
        return sDescripcion;
    }
    
    private int getTipo() {
        return iTipo;
    }
    
    private double getLatitud() {
        return dLatitud;
    }
    
    private double getLongitud() {
        return dLongitud;
    }
    
    private Date getFecha() {
        return dteFecha;
    }
    
    private int getHora() {
        return iHora;
    }
    
    private int getMinutos() {
        return iMinutos;
    }
    
    private void setDescripcion(String sDesc) {
        sDescripcion = sDesc;
    }
    
    private void setTipo(int iType) {
        iTipo = iType;
    }
    
    private void setLatitud(double dLat) {
        dLatitud = dLat;
    }
    
    private void setLongitud(double dLong) {
        dLongitud = dLong;
    }
    
    private void setFecha(Date dteDate) {
        dteFecha = dteDate;
    }
    
    private void setFecha(int iY, int iM, int iD) {
        String sDate = iY + "-" + iM + "-" + iD;
        dteFecha = valueOf(sDate);
    }
    
    private void setHora(int iH) {
        iHora = iH;
    }
    
    private void setMinutos(int iM) {
        iMinutos = iM;
    }
}
