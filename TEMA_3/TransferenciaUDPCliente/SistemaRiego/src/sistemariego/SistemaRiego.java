/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemariego;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Alejandro
 */
public class SistemaRiego extends TimerTask{

    @Override
    public void run() {
        System.out.println("Regando...");
    }
    
    

    
    public static void main(String[] args) {
        Timer temporizador = new Timer();
        temporizador.schedule(new SistemaRiego(), 1000, 2000);
    }
    
}
