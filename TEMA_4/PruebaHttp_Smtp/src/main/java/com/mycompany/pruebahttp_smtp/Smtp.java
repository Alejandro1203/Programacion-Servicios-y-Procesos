package com.mycompany.pruebahttp_smtp;

import com.sun.mail.imap.IMAPBodyPart;
import jakarta.mail.Address;
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
import java.util.Properties;

/**
 *
 * @author Alejandro
 */
public class Smtp {
    private static Properties properties;
    private static Session session;
    
    private static void setPropiedadesSMTP() {
        properties = System.getProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "mail.gmx.es");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", true);
        session = Session.getInstance(properties, null);
    }
    
    private static Transport conectarServidorSMTP(String direccionEmail, String password) throws NoSuchProviderException, MessagingException {
        Transport t = (Transport) session.getTransport("smtp");
        t.connect(properties.getProperty("mail.stmp.host"), direccionEmail, password);
        
        return t;        
    }
    
    private static Message crearNucleoMensaje(String emisor, String destinatario, String asunto) throws AddressException, MessagingException {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(emisor));   
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensaje.setSubject(asunto);
        
        return mensaje;
    }
    
    private static Message crearMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String pathFichero) throws MessagingException, IOException{
        Message mensaje = crearNucleoMensaje(emisor, destinatario, asunto);
        
        BodyPart body = new MimeBodyPart();
        body.setText(textoMensaje);
        
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.attachFile(new File(pathFichero));
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);
        multipart.addBodyPart(mimeBodyPart);
        mensaje.setContent(multipart);
        
        return mensaje;
    }
    
    public static void enviarMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String pathFichero,
                                        String direccionEmail, String password) throws MessagingException, IOException {
        
        setPropiedadesSMTP();
        
        Message mensaje = crearMensajeConAdjunto(emisor, destinatario, asunto, textoMensaje, pathFichero);
        Transport t = conectarServidorSMTP(direccionEmail, password);
        
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();        
    }  
}