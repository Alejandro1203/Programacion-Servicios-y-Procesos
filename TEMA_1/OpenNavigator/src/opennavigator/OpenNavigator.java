package opennavigator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author Alejandro
 */
public class OpenNavigator {
    
    public static void main(String[] args) {
        
        try {            
            ProcessBuilder procesoBuilder = new ProcessBuilder (
            "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", "https://www.python.org/");
            
            procesoBuilder.start();            
            Map<String, String> environment = procesoBuilder.environment();
            System.out.println(environment);
        } catch (IOException e) {
            e.printStackTrace();            
        }
    }
}