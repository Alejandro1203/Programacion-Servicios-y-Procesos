package com.mycompany.pruebahttp_smtp;

import jakarta.mail.Session;
import java.util.Properties;

/**
 *
 * @author Alejandro
 */
public class Smtp {
    private Properties properties;
    private Session session;
    
    private void setPropiedadesSMTP() {
        properties = System.getProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "mail.gmx.es");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", true);
        session = 
    }
            
    
}
