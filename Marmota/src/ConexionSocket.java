
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JuanJosé
 */
public class ConexionSocket implements Runnable{
    private int iType;
    private String sUsuario;
    private Database dbConexion;
    private Socket socConexion;
    private BufferedReader bfrEntrada;
    private PrintWriter dosSalida;
    public ConexionSocket(Socket socCon){
        this.socConexion=socCon;
        try{
        bfrEntrada = new BufferedReader(new InputStreamReader(socCon.
                getInputStream()));
        dosSalida = new PrintWriter(new BufferedWriter(
            new OutputStreamWriter(socCon.getOutputStream())),true);
        this.iType = Integer.parseInt(bfrEntrada.readLine());
        System.out.println(iType);
        }catch(Exception e){
            System.out.println(e);
        }
        dbConexion = new Database();
        new Thread(this).start();
    }

    @Override
    public void run() {
        if(iType == 1){
            try {
                String strDatos = bfrEntrada.readLine();
                System.out.println(strDatos);
               if(dbConexion.login(strDatos)){
                    dosSalida.println('2');
                    int iGato = strDatos.indexOf('#');
                    
                    sUsuario = strDatos.substring(0,iGato);
                    while(!socConexion.isClosed()){
                        int iActividad = Integer.parseInt(bfrEntrada.readLine());
                        if(iActividad == 5){
                            String strDescripcion = bfrEntrada.readLine();
                            int iTipo = Integer.parseInt(bfrEntrada.readLine());
                            double dLatitud = Double.
                                    parseDouble(bfrEntrada.readLine());
                            double dLongitud = Double
                                    .parseDouble(bfrEntrada.readLine());
                            int iDia,iMes,iYear,iHora,iMinutos;
                            iDia = 14;
                            iMes = 5;
                            iYear = 1996;
                            iHora = 10;
                            iMinutos = 30;
                            if(dbConexion.incidente(new Incidente(sUsuario,
                            strDescripcion,iTipo,dLatitud,dLongitud,
                            iYear,iMes,iDia,iHora,iMinutos)));
                        }else if(iActividad == 6){
                            double dLongitud = Double.parseDouble(bfrEntrada
                                    .readLine());
                            double dLatitud = Double.parseDouble(bfrEntrada
                                    .readLine());
                            int iHInicio = Integer.parseInt(bfrEntrada
                                    .readLine());
                            int iMInicio = Integer.parseInt(bfrEntrada
                                    .readLine());
                            int iHFinal = Integer.parseInt(bfrEntrada
                                    .readLine());
                            int iMFinal = Integer.parseInt(bfrEntrada
                                    .readLine());
                            List<Incidente> lklIncidentes = dbConexion
                                    .filtrarIncidenteHora(dLatitud,dLongitud
                                          ,iHInicio,iMInicio, iHFinal, iMFinal);
                            dosSalida.println(lklIncidentes.size());
                            for(Incidente inElemento : lklIncidentes){
                                dosSalida.println(inElemento.getDescripcion());
                                dosSalida.println(inElemento.getFecha()
                                        .getDay());
                                dosSalida.println(inElemento.getFecha()
                                        .getMonth());
                                dosSalida.println(inElemento.getFecha()
                                        .getYear());
                                dosSalida.println(inElemento.getHora());
                                dosSalida.println(inElemento.getMinutos());
                                dosSalida.println(inElemento.getLatitud());
                                dosSalida.println(inElemento.getLongitud());
                                dosSalida.println(inElemento.getTipo());
                                
                            }
                        }
                    }
                     socConexion.close();
                }else{
                    dosSalida.println('3');
                    socConexion.close();
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConexionSocket.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }else if(iType == 4){
            try {
                String strDatos = bfrEntrada.readLine();
               if(dbConexion.register(strDatos)){
                    dosSalida.println('2');
                     socConexion.close();
                }else{
                    dosSalida.println('3');
                    socConexion.close();
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConexionSocket.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
