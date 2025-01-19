package com.psp.jokeapi;
  
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author Alejandro
 */
public class JokeAPI {
    
    public static String getJoke(String url) {
        String joke = ""; 
        
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        
        try {
            HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());
            
            if(response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                joke = root.get("joke").asText();
                
            } else {
                joke = "ERROR al encontrar el chiste " + response.statusCode();
            }
            
        } catch(IOException | InterruptedException e) {
            System.out.println(e);
        }
        
        return joke;
    }

    public static void main(String[] args) {
        String esquema = "https://v2.";
        String servidor = "jokeapi.dev/joke/";
        String categoria = "Programming?";
        String lenguaje = "lang=es";
        String tipo_broma = "type=single";
        String separador = "&";
        
        String url = esquema + servidor + categoria + lenguaje + separador + tipo_broma;
        
        System.out.println(getJoke(url));
    }
}