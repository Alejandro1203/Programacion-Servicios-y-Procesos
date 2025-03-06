/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package confidencialidad_identidad;

import Managers.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Interfaz_Confidencialidad_Identidad extends javax.swing.JFrame {
    
    private static byte[] firma;

    /**
     * Creates new form Interfaz_Confidencialidad_Idetidad
     */
    public Interfaz_Confidencialidad_Identidad() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_claves = new javax.swing.JButton();
        txt_mensaje = new javax.swing.JTextField();
        btn_cifrar_firmar = new javax.swing.JButton();
        btn_validar_descifrar = new javax.swing.JButton();
        txt_mensaje_descifrado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_claves.setText("Generar Claves");
        btn_claves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clavesMouseClicked(evt);
            }
        });

        btn_cifrar_firmar.setText("Cifrar y firmar");
        btn_cifrar_firmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cifrar_firmarMouseClicked(evt);
            }
        });

        btn_validar_descifrar.setText("Validar y descifrar");
        btn_validar_descifrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_validar_descifrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_validar_descifrar)
                        .addGap(87, 87, 87)
                        .addComponent(txt_mensaje_descifrado, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_claves)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btn_cifrar_firmar)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_mensaje_descifrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_claves)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cifrar_firmar))
                        .addGap(44, 44, 44)
                        .addComponent(btn_validar_descifrar)))
                .addContainerGap(224, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clavesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clavesMouseClicked
        
        try {
            KeyPair claves = ClavesManager.generarClaves();
            ClavesManager.guardarClaves(claves);
            InterfaceManager.generateMessagePopUp(this, "Claves generadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_clavesMouseClicked

    private void btn_cifrar_firmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cifrar_firmarMouseClicked
        try {
            String clave = InterfaceManager.fileChooser();
            System.out.println(clave);
            PrivateKey privateKey = ClavesManager.getClavePrivada(clave);
            
            InterfaceManager.createFile("datoscifrados.rsa");
            
            byte[] mensajeCifrado = RSAManager.cifrar(txt_mensaje.getText(), privateKey);
            
            System.out.println(Arrays.toString(mensajeCifrado));
            
            InterfaceManager.writeFile("datoscifrados.rsa", mensajeCifrado);
            
            firma = FirmaDigitalManager.firmaDigital(clave, "C:\\Users\\ALUMNO2425\\Documents\\GitHub\\Programacion-Servicios-y-Procesos\\Confidencialidad_Identidad\\datoscifrados.rsa");
            
            if(firma[0] == 0) {
                InterfaceManager.generateErrorPopUp(this, "Fallo al firmar");
                btn_validar_descifrar.setEnabled(false);
            } else {
                InterfaceManager.generateMessagePopUp(this, "Firmado correctamente");
                btn_validar_descifrar.setEnabled(true);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_cifrar_firmarMouseClicked

    private void btn_validar_descifrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_validar_descifrarMouseClicked
        try {
            
            String clave = InterfaceManager.fileChooser();
            
            if(FirmaDigitalManager.firmaEmisor(clave, "datoscifrados.rsa", firma)) {
                InterfaceManager.generateMessagePopUp(this, "Mensaje verificado");
                
                //Descifrar
                
                try {
            byte[] mensajeCifrado = Files.readAllBytes(new File("datoscifrados.rsa").toPath());
            byte[] mensajeDescifrado = RSAManager.descifrar(mensajeCifrado, ClavesManager.getClavePublica(clave));
            
//            String mens = new String(mensajeDescifrado, StandardCharsets.UTF_8);
            txt_mensaje_descifrado.setText(new String(mensajeDescifrado, StandardCharsets.UTF_8));
//            System.out.println(new String(mensajeDescifrado, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
            } else {
                InterfaceManager.generateErrorPopUp(this, "Error de validación");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_validar_descifrarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Confidencialidad_Identidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Confidencialidad_Identidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cifrar_firmar;
    private javax.swing.JButton btn_claves;
    private javax.swing.JButton btn_validar_descifrar;
    private javax.swing.JTextField txt_mensaje;
    private javax.swing.JTextField txt_mensaje_descifrado;
    // End of variables declaration//GEN-END:variables
}
