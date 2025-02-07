package com.mycompany.pruebahttp_smtp;

import java.util.Scanner;

/**
 *
 * @author Alejandro 
 */
public class PruebaHttp_Smtp {
    
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String esquema = "https://";
        String servidor = "fakestoreapi.com/";
        String categoria = "products";
        
        System.out.print("Indique un ID de un producto: ");
        int id = sc.nextInt();
        
        String ruta = esquema + servidor + categoria + "/" + id;
        String respuesta = Http.getRequest(ruta, "id");
        
        System.out.println(respuesta);
    }
}
