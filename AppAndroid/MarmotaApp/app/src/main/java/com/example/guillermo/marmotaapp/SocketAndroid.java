package com.example.guillermo.marmotaapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jorge on 20/02/2016.
 */
public class SocketAndroid implements Runnable{
    Socket socSalida;
    int iOperacion;
    String datos;
    public Socket crearSocket(){
        iOperacion = 0;
        new Thread(this).start();
        try{
            Thread.sleep(500);
            return socSalida;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void enviarDatos(String data,Socket sc){
        socSalida = sc;
        datos = data;
        iOperacion = 1;
        new Thread(this).start();
    }
    public String recibirDatos(Socket sc){
        socSalida = sc;
        iOperacion=2;
        new Thread(this).start();
        try{
            Thread.sleep(500);
        }catch(Exception e){
            System.out.println(e);
        }
        return datos;
    }
    @Override
    public void run() {
        try {
            if(iOperacion == 0){
                socSalida = new Socket("10.12.175.205",7890);
            }else if(iOperacion == 1){
                PrintWriter prSalida = new PrintWriter(new OutputStreamWriter(socSalida.getOutputStream()));
                prSalida.println(datos);
            }else if(iOperacion==2){
                BufferedReader bffEntrada = new BufferedReader(new InputStreamReader(socSalida.getInputStream()));
                datos = bffEntrada.readLine();
            }
            } catch (IOException e) {
            System.out.println(e);
        }
    }


}
