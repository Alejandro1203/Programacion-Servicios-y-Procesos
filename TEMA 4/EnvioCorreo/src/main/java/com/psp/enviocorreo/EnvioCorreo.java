package com.psp.enviocorreo;

import com.sun.mail.imap.IMAPBodyPart;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Alejandro
 */
public class EnvioCorreo {
    
    private Properties propiedades;
    private Session sesion;
    
    private void setPropiedadesServidorSMTp() {
        propiedades = System.getProperties();
        propiedades.put("mail.smtp.auth", true);
        propiedades.put("mail.smtp.host", "mail.gmx.es");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.starttls.enable", true);
        sesion = Session.getInstance(propiedades, null);
    }
    
    private Transport conectarServidorSMTP(String direccionEmail, String password) throws NoSuchProviderException, MessagingException {
        Transport t = (Transport) sesion.getTransport("smtp");
        t.connect(propiedades.getProperty("mail.smtp.host"), direccionEmail, password);
        
        return t;
    }
    
    private Message crearNucleoMensaje(String emisor, String destinatario, String asunto) throws AddressException, MessagingException {
        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(emisor));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        
        return mensaje;
    }
    
    private Message crearMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String pathFichero) throws MessagingException, IOException {
        Message mensaje = crearNucleoMensaje(emisor, destinatario, asunto);
        
        // Cuerpo del mensaje
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(textoMensaje);
        
        // Adjunto de mensaje
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.attachFile(new File(pathFichero));
        
        // Composición del mensaje (Cuerpo + adjunto)
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(mimeBodyPart);
        mensaje.setContent(multipart);        
        
        return mensaje;
    }
    
    public void enviarMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String direccionEmail, String password, String pathFichero) throws MessagingException, IOException {
        setPropiedadesServidorSMTp();
        
        Message mensaje = crearMensajeConAdjunto(emisor, destinatario, asunto, textoMensaje, pathFichero);
        Transport t = conectarServidorSMTP(direccionEmail, password);
        
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }

    public static void main(String[] args) {
        
        try {
            String emailEmisor = "ale.fernandez@gmx.es";
            String passwordEmisor = "PSPIESBelen";
            
            EnvioCorreo envioCorreo = new EnvioCorreo();
            envioCorreo.enviarMensajeConAdjunto(emailEmisor,  "alefg2003@gmail.com",
                    "Aviso de entrega de factura", "El importe de la factura"
                    + " es 113,72€", emailEmisor, passwordEmisor,"..\\..\\Visual Studio 2022\\Componente\\Componente.sln"
                            + "");
        } catch(IOException | MessagingException e) {
            System.out.println(e);
        }
    }
}
