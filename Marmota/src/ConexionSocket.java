
import java.io.*;
import java.net.*;
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
                            if(dbConexion.incidente(sUsuario,strDescripcion,
                                    iTipo,dLatitud,dLongitud,iDia,iMes,
                                    iYear,iHora,iMinutos));
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
