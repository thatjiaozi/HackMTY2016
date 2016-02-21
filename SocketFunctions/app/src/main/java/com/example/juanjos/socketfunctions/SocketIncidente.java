package com.example.juanjos.socketfunctions;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by JuanJosé on 21/02/2016.
 */
public class SocketIncidente implements Runnable {
    private SocketLogin socPrincipal;
    private BufferedReader buffEntrada;
    private PrintWriter prSalida;
    private boolean boolActivo;
    private int iAccion;
    private Intent inParametros;
    private LinkedList<Incidente> lklSalida;
    public SocketIncidente(SocketLogin socParametro){
        this.socPrincipal = socParametro;
        buffEntrada = socPrincipal.getBuffEntrada();
        prSalida = socPrincipal.getPrSalida();

    }
    public void reportarIncidente(String sDesc,int iTipo,double dLatitud,
                                  double dLongitud,int iDia, int iMes, int iYear,
                                  int iHora, int iMinutos){
        prSalida.println("5");
        prSalida.println(sDesc);
        prSalida.println(iTipo);
        prSalida.println(dLatitud);
        prSalida.println(dLongitud);
        prSalida.println(iDia);
        prSalida.println(iMes);
        prSalida.println(iYear);
        prSalida.println(iHora);
        prSalida.println(iMinutos);
    }
    public LinkedList<Incidente> getIncidente(double dLatitud, double dLongitud,
                                               int iHoraInicio, int iMinutoInicio,
                                               int iHoraFinal, int iMinutoFinal) {
        prSalida.println("6");
        inParametros = new Intent();
        inParametros.putExtra("Latitud", dLatitud);
        inParametros.putExtra("Longitud", dLongitud);
        inParametros.putExtra("HoraInicio", iHoraInicio);
        inParametros.putExtra("MinutoInicio", iMinutoInicio);
        inParametros.putExtra("HoraFinal", iHoraFinal);
        inParametros.putExtra("MinutoFinal", iMinutoFinal);
        iAccion = 1;
        boolActivo = true;
        new Thread(this).start();
        while(boolActivo){
            try{
                Thread.sleep(100);

            }catch (Exception e){
                System.out.println(e);
            }
        }
        return lklSalida;
    }
    public LinkedList<Incidente> getIncidente(double dLatitud, double dLongitud,
                                              int iHoraInicio, int iMinutoInicio,
                                              int iHoraFinal, int iMinutoFinal,int iTipo) {

        inParametros = new Intent();
        inParametros.putExtra("Latitud", dLatitud);
        inParametros.putExtra("Longitud", dLongitud);
        inParametros.putExtra("HoraInicio", iHoraInicio);
        inParametros.putExtra("MinutoInicio", iMinutoInicio);
        inParametros.putExtra("HoraFinal", iHoraFinal);
        inParametros.putExtra("MinutoFinal", iMinutoFinal);
        inParametros.putExtra("Tipo", iTipo);
        iAccion = 3;
        boolActivo = true;
        new Thread(this).start();
        while (boolActivo) {
            try {
                Thread.sleep(100);
                System.out.println("puto");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return lklSalida;
    }
    public void getIncidenteT() {
        lklSalida = new LinkedList<Incidente>();
        prSalida.println(inParametros.getExtras().getSerializable("Latitud"));
        prSalida.println(inParametros.getExtras().getSerializable("Longitud"));
        prSalida.println(inParametros.getExtras().getSerializable("HoraInicio"));
        prSalida.println(inParametros.getExtras().getSerializable("MinutoInicio"));
        prSalida.println(inParametros.getExtras().getSerializable("HoraFinal"));
        prSalida.println(inParametros.getExtras().getSerializable("MinutoFinal"));
        try {
            int iCantidad = Integer.parseInt(buffEntrada.readLine());
            for (int iC = 0; iC < iCantidad; iC++) {
                Incidente inTemp;
                inTemp = new Incidente();
                inTemp.setDescripcion(buffEntrada.readLine());
                int iDia = Integer.parseInt(buffEntrada.readLine());
                int iMes = Integer.parseInt(buffEntrada.readLine());
                int iYear = Integer.parseInt(buffEntrada.readLine());
                inTemp.setFecha(iYear,iMes,iDia);
                inTemp.setHora(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setMinutos(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setLatitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setLongitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setTipo(Integer.parseInt(buffEntrada.readLine()));
                lklSalida.add(inTemp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        boolActivo = false;
    }
    public void getIncidenteTipoT() {
        lklSalida = new LinkedList<Incidente>();
        prSalida.println("8");
        prSalida.println(inParametros.getExtras().getSerializable("Latitud"));
        prSalida.println(inParametros.getExtras().getSerializable("Longitud"));
        prSalida.println(inParametros.getExtras().getSerializable("HoraInicio"));
        prSalida.println(inParametros.getExtras().getSerializable("MinutoInicio"));
        prSalida.println(inParametros.getExtras().getSerializable("HoraFinal"));
        prSalida.println(inParametros.getExtras().getSerializable("MinutoFinal"));
        prSalida.println(inParametros.getExtras().getSerializable("Tipo"));
        try {
            int iCantidad = Integer.parseInt(buffEntrada.readLine());
            System.out.println(iCantidad);
            for (int iC = 0; iC < iCantidad; iC++) {
                Incidente inTemp;
                inTemp = new Incidente();
                inTemp.setDescripcion(buffEntrada.readLine());
                int iDia = Integer.parseInt(buffEntrada.readLine());
                int iMes = Integer.parseInt(buffEntrada.readLine());
                int iYear = Integer.parseInt(buffEntrada.readLine());
                inTemp.setFecha(iYear,iMes,iDia);
                inTemp.setHora(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setMinutos(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setLatitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setLongitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setTipo(Integer.parseInt(buffEntrada.readLine()));
                lklSalida.add(inTemp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        boolActivo = false;
    }
    public LinkedList<Incidente> getIncidentesUsuario(){
        iAccion=2;


        new Thread(this).start();

        boolActivo = true;
        while(boolActivo){
            try{
                Thread.sleep(100);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return lklSalida;
    }
    public void getIncidentesUsuarioT(){
        lklSalida = new LinkedList<Incidente>();
        prSalida.println("7");
        try {
            BufferedReader temporal = socPrincipal.getBuffEntrada();
            int iCantidad = Integer.parseInt(temporal.readLine());
            System.out.println("Cantidad: "+iCantidad);
            for (int iC = 0; iC < iCantidad; iC++) {
                Incidente inTemp;
                inTemp = new Incidente();
                inTemp.setDescripcion(buffEntrada.readLine());
                int iDia = Integer.parseInt(buffEntrada.readLine());
                int iMes = Integer.parseInt(buffEntrada.readLine());
                int iYear = Integer.parseInt(buffEntrada.readLine());
                inTemp.setFecha(iYear,iMes,iDia);
                inTemp.setHora(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setMinutos(Integer.parseInt(buffEntrada.readLine()));
                inTemp.setLatitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setLongitud(Double.parseDouble(buffEntrada.readLine()));
                inTemp.setTipo(Integer.parseInt(buffEntrada.readLine()));
                lklSalida.add(inTemp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        boolActivo = false;
    }
    @Override
    public void run() {
        if(iAccion==1)
            getIncidenteT();
        else if(iAccion == 2)
            getIncidentesUsuarioT();
        else if(iAccion == 3)
            getIncidenteTipoT();

    }
}
