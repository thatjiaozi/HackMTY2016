
import java.net.*;
/**
 * Clase principal del servidor
 * creada a las 3:15 pm 
 * Funcionalidades:
 *  Login
 * @author JuanJosé
 */
public class Servidor{
    private final static int iPuerto = 7890;
    private static ServerSocket svsServidor;
    public static void main(String[] args){
        try{
        svsServidor = new ServerSocket(iPuerto);
        while (true){
            Socket scEntrante = svsServidor.accept();
            System.out.println("conexion entrante de "+scEntrante.toString());
            ConexionSocket conEntrante = new ConexionSocket(scEntrante);
        }
        }catch(Exception e){
            System.out.println("main serv"+e);
        }
    }

   
}
