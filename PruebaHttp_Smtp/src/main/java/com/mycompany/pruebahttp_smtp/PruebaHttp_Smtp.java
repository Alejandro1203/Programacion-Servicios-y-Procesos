package com.mycompany.pruebahttp_smtp;

import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Alejandro 
 */
public class PruebaHttp_Smtp {
    
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws MessagingException, IOException {
        Smtp smtp = new Smtp();
        
        String esquema = "https://";
        String servidor = "fakestoreapi.com/";
        String categoria = "products";
        
        System.out.print("Indique un ID de un producto: ");
        int id = sc.nextInt();
        
        String ruta = esquema + servidor + categoria + "/" + id;
        String respuesta = Http.getRequest(ruta);
        
        System.out.println(respuesta);
        
        Smtp.enviarMensajeConAdjunto("ale.fernandez@gmx.es", "alefg2003@gmail.com", "Producto",
                    respuesta,"cayenne_11zon.jpg", "ale.fernandez@gmx.es", "PSPIESBelen");
    }
}