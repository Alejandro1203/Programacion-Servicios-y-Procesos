package transferenciaudpcliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Alejandro
 */
public class TransferenciaUDPCliente {

    public static void main(String[] args) throws InterruptedException {
        
        String strMensaje = "";
        DatagramSocket socketUDP;
        
        try {
            System.out.println("(Cliente): Estableciendo parametros de conexion...");
            InetAddress hostServidor = InetAddress.getByName("localhost");
            int puertoServidor = 33333;
            
            System.out.println("(Cliente): Creando socket...");
            socketUDP = new DatagramSocket();
            
            System.out.println("(Cliente) Enviando datagrama....");
            
            byte[] mensaje;
            DatagramPacket peticion;
                 
            for (int indice = 0; indice < 10000; indice++) {
                strMensaje = "Mensaje: " + indice;
                //Thread.sleep(10);
                mensaje = strMensaje.getBytes();
                peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
                socketUDP.send(peticion);
            }
            
            String fin = "FIN";
            mensaje = fin.getBytes();
            peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServidor);
            socketUDP.send(peticion);           
            
//            System.out.println("(Cliente) Recibiendo datagrama....");
//            byte[] buffer = new byte[64];
//            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, hostServidor, puertoServidor);
//            socketUDP.receive(respuesta);
//            System.out.println("(Cliente): Mensaje recibido: " + new String(buffer));
            
            System.out.println("(Cliente): Cerrando socket...");
            socketUDP.close();
            System.out.println("(Cliente) Socket cerrado.");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
