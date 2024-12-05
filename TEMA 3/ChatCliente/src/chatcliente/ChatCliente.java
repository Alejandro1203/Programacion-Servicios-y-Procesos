package chatcliente;

import chatcliente.interfaz.VentanaCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class ChatCliente {

    static Scanner sc = new Scanner(System.in);
    
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    
    public ChatCliente(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        VentanaCliente ventanaCliente = new VentanaCliente();
        ventanaCliente.setVisible(true);
    }
    
    public void abrirConexiones() throws IOException {
        System.out.println("(Cliente) Estableciento conexion...");
        socket = new Socket(serverIP, serverPort);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        System.out.println("(Cliente) Conexion establecida.");
    }
    
    public void cerrarConexiones() throws IOException {
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
        ChatCliente cliente = new ChatCliente("localhost", 33333);
        
        try {
            cliente.abrirConexiones();
            cliente.abrirCanalesDeTexto();
            
            System.out.println("Mensaje del servidor ------> " + cliente.leerMensajeTexto());
            cliente.enviarMensajeTexto(sc.nextLine());
            
            System.out.println("Contenido del archivo\n " + cliente.leerMensajeTexto());
            
            cliente.cerrarCanalesDeTexto();
            cliente.cerrarConexiones();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}