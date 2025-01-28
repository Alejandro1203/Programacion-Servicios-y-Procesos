package com.psp.tododefilezilla;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author Alejandro
 */
public class TodoDeFilezilla {
    
    private FTPClient clienteFTP;
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 21;
    private static final String USUARIO = "usuario";
    private static final String PASSWORD = "12345678";
    
    public TodoDeFilezilla() {
        clienteFTP = new FTPClient();
    }
    
    private void conectar() throws IOException {
        clienteFTP.connect(SERVIDOR, PUERTO);
        int respuesta = clienteFTP.getReplyCode();
        
        if(!FTPReply.isPositiveCompletion(respuesta)) {
            clienteFTP.disconnect();
            throw new IOException("Error al conectar con el servidor FTP");
        }
        
        boolean credencialesOK = clienteFTP.login(USUARIO, PASSWORD);
        
        if(!credencialesOK) {
            throw new IOException("Error al conectar con el servidor FTP. Credenciales incorrectas.");
        }
        
        clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
    }
    
    private void desconectar() throws IOException {
        clienteFTP.disconnect();
    }
    
    private void descargaFicheros(String ruta) throws IOException {
        FTPFile[] files = clienteFTP.listFiles(ruta);
        
        for (FTPFile file : files) {
            descargarFichero(file.getName());
            System.out.println(file.getName());
        }
    }
    
    private boolean descargarFichero(String filename) throws FileNotFoundException, IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(filename));
        boolean recibido = clienteFTP.retrieveFile(filename, os);
        os.close();
        
        return recibido;
    }

    public static void main(String[] args) {
        
        TodoDeFilezilla tdf = new TodoDeFilezilla();
        
        try {
            tdf.conectar();
            System.out.println("Conectado");
            
            tdf.descargaFicheros("/");
            
            tdf.desconectar();
            System.out.println("Desconectado");
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
