package resuelto3.pkg1_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Alejandro
 */
public class Cliente {
    
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private Cliente(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Estableciendo conexión...");
        socket = new Socket(serverIP, serverPort);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();

        System.out.println("(Cliente) Conexión establecida.");
    }
    
    public void stop() throws IOException {
        System.out.println("(Cliente) Cerrando conexiones...");
        inputStream.close();
        outputStream.close();
        socket.close();  
        System.out.println("(Cliente) Conexiones cerradas.");
    }
    
    public void abrirCanalesDeTexto() {
        System.out.println("(Cliente) Abriendo canales de texto...");
        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
        System.out.println("(Cliente) Canales de texto abiertos.");
    }
    
    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(Cliente) Cerrando canales de texto...");
        dataInputStream.close();
        dataOutputStream.close();
        System.out.println("(Cliente) Canales de texto cerrados.");
    }
    
    public String leerMensajeTexto() throws IOException {
        System.out.println("(Cliente) Leyendo mensaje...");
        String mensaje = dataInputStream.readUTF();
        System.out.println("(Cliente) Mensaje leido.");
        return mensaje;
    }
    
    public void enviarMensajeTexto(String mensaje) throws IOException {
        System.out.println("(Cliente) Enviado mensaje...");
        dataOutputStream.writeUTF(mensaje);
        System.out.println("(Cliente) Mensaje enviado.");
    }
    
    public static void main(String[] args) {
        
        try {
            Cliente cliente = new Cliente("localhost",49171);
            cliente.start();
            cliente.abrirCanalesDeTexto();
            
            cliente.enviarMensajeTexto("Que me dices");
            
            String mensajeRecibido = cliente.leerMensajeTexto();
            System.out.println("(Cliente) Mensaje recibido: " + mensajeRecibido);
            
            cliente.cerrarCanalesDeTexto();
            cliente.stop();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}