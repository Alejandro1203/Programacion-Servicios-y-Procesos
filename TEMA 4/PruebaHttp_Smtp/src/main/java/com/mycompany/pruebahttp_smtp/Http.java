package com.mycompany.pruebahttp_smtp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Http {
    
    public static String getRequest(String ruta) {
        String respuesta = "";
        
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ruta))
                .build();
        
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if(response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                respuesta += "Id: " + root.get("id").asText() + "\n";
                respuesta += "Title: " + root.get("title").asText() + "\n";
                respuesta += "Price: " + root.get("price").asText() + "\n";
                respuesta += "Description: " + root.get("description").asText() + "\n";
                respuesta += "Category: " + root.get("category").asText();
            } else {
                respuesta = "ERROR";
            }
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Http.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;
    }
}