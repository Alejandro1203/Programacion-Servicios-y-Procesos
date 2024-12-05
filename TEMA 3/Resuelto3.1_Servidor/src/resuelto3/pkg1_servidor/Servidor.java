package resuelto3.pkg1_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Alejandro
 */
public class Servidor {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Servidor(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }
    
    public void start() throws IOException {
        System.out.println("(Servidor) Esperando conexiones...");
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        System.out.println("(Servidor) Conexi�n establecida.");
    }
    
    public void stop() throws IOException {
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

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor(49171);
            servidor.start();
            servidor.abrirCanalesDeTexto();
            
            String mensaje = servidor.leerMensajeTexto();
            System.out.println("Mensaje del cliente: " + mensaje);
            servidor.enviarMensajeTexto("Lo que te cuento");
            servidor.cerrarCanalesDeTexto();
            servidor.stop();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}