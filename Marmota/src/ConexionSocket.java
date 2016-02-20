
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
    //private Database dbConexion;
    private Socket socConexion;
    private BufferedReader bfrEntrada;
    private DataOutputStream dosSalida;
    public ConexionSocket(Socket socCon){
        this.socConexion=socCon;
        try{
        bfrEntrada = new BufferedReader(new InputStreamReader(socCon.
                getInputStream()));
        dosSalida = new DataOutputStream(socCon.getOutputStream());
        this.iType = Integer.parseInt(bfrEntrada.readLine());
        }catch(Exception e){
            System.out.println(e);
        }
        
        new Thread(this).start();
    }

    @Override
    public void run() {
        if(iType == 1){
            try {
                String strDatos = bfrEntrada.readLine();
               /* if(dbConexion.login(strDatos)){
                    dosSalida.writeInt(2);
                }else{
                    dosSalida.writeInt(3);
                }*/
            } catch (IOException ex) {
                Logger.getLogger(ConexionSocket.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
