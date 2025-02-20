package chatservidor;

import chatservidor.interfaz.VentanaServidor;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alejandro
 */
public class ChatServidor {

    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;
    
    public ChatServidor (int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        VentanaServidor ventanaServidor = new VentanaServidor();
        ventanaServidor.setVisible(true);
    }
    
    public void abrirConexiones() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        System.out.println("(Servidor) Conexion establecida.");
    }
    
    public void cerrarConexiones() throws IOException {
        System.out.println("(Servidor) Cerrando conexiones...");
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor) Conexiones cerradas.");
    }
    
    public void abrirCanalesDeTexto() {
        System.out.println("(Servidor) Abriendo canales de texto...");
        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
        System.out.println("(Servidor) Canales de texto abiertos.");
    }
    
    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(Servidor) Cerrando canales de texto...");
        dataInputStream.close();
        dataOutputStream.close();
        System.out.println("(Servidor) Canales de texto cerrados.");
    }
    
    public String leerMensajeTexto() throws IOException {
        System.out.println("(Servidor) Leyendo mensaje...");
        String mensaje = dataInputStream.readUTF();
        System.out.println("(Servidor) Mensaje leido.");
        
        return mensaje;
    }
    
    public void enviarMensajeTexto(String mensaje) throws IOException {
        System.out.println("(Servidor) Enviado mensaje...");
        dataOutputStream.writeUTF(mensaje);
        System.out.println("(Servidor) Mensaje enviado.");
    }
    
//    public String leerArchivo(String ruta){
//        System.out.println("Ruta archivo: " + ruta);
//        String contenido = "";
//        String linea;
//        
//        try {
//            bufferedReader = new BufferedReader(new FileReader(ruta));
//            linea = bufferedReader.readLine();
//            
//            while (linea != null){
//                contenido += linea + "\n";
//                linea = bufferedReader.readLine();
//            }
//            
//            System.out.println("Enviando archivo.");
//        } catch (IOException e) {
//            System.out.println(e);
//        } 
//        
//        return contenido;
//    }

    public static void main(String[] args) {
        
        
        try {
            ChatServidor servidor = new ChatServidor(33333);
            
            servidor.abrirConexiones();
            servidor.abrirCanalesDeTexto();

            servidor.enviarMensajeTexto("Indique mensaje: ");
            servidor.enviarMensajeTexto(servidor.leerMensajeTexto());

            servidor.cerrarCanalesDeTexto();
            servidor.cerrarConexiones();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
