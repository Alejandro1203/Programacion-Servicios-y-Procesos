package confidencialidad;

import Managers.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Interfaz extends javax.swing.JFrame {
    
    private static final String ARCHIVO_CIFRADO = "datoscifrados.rsa";
    private static byte[] firma;

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
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

        btn_cifrar_firmar = new javax.swing.JButton();
        edt_mensaje = new javax.swing.JTextField();
        btn_claves = new javax.swing.JButton();
        btn_descifrar_validar = new javax.swing.JButton();
        txt_descifrado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_cifrar_firmar.setText("Cifrar y firmar");
        btn_cifrar_firmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cifrar_firmarMouseClicked(evt);
            }
        });

        btn_claves.setText("Generar Claves");
        btn_claves.setToolTipText("");
        btn_claves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clavesMouseClicked(evt);
            }
        });

        btn_descifrar_validar.setText("Descifrar y validar");
        btn_descifrar_validar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_descifrar_validarMouseClicked(evt);
            }
        });

        txt_descifrado.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(edt_mensaje))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(btn_claves))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(btn_descifrar_validar)))
                        .addGap(0, 114, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cifrar_firmar)
                    .addComponent(txt_descifrado, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btn_claves)
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cifrar_firmar)
                    .addComponent(edt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_descifrar_validar)
                    .addComponent(txt_descifrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cifrar_firmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cifrar_firmarMouseClicked
        InterfaceManager.createFile(ARCHIVO_CIFRADO);
        String clavePrivada = InterfaceManager.fileChooser();
        
        try {
            byte[] mensajeCifrado = RSAManager.cifrar(edt_mensaje.getText(), ClavesManager.getClavePrivada(clavePrivada));
            InterfaceManager.writeFile(ARCHIVO_CIFRADO, mensajeCifrado);
            
            firma = FirmaDigitalManager.firmaDigital(clavePrivada, ARCHIVO_CIFRADO);
            
            if(firma[0] == 0) {
                InterfaceManager.generateErrorPopUp(this, "Fallo al firmar");
            } else {
                InterfaceManager.generateMessagePopUp(this, "Firmado correctamente");
            }
        } catch (Exception ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_cifrar_firmarMouseClicked

    private void btn_clavesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clavesMouseClicked
        
        try {
            KeyPair claves = ClavesManager.generarClaves();
            ClavesManager.guardarClaves(claves);
            InterfaceManager.generateMessagePopUp(this, "Claves generadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_clavesMouseClicked

    private void btn_descifrar_validarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_descifrar_validarMouseClicked
        
        try {
            byte[] mensajeCifrado = InterfaceManager.readBytesFile(ARCHIVO_CIFRADO);
            byte[] mensajeDescifrado = RSAManager.descifrar(mensajeCifrado, ClavesManager.getClavePublica(InterfaceManager.fileChooser()));
            
            txt_descifrado.setText(new String(mensajeDescifrado, StandardCharsets.UTF_8));
        } catch (Exception ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_descifrar_validarMouseClicked

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cifrar_firmar;
    private javax.swing.JButton btn_claves;
    private javax.swing.JButton btn_descifrar_validar;
    private javax.swing.JTextField edt_mensaje;
    private javax.swing.JTextField txt_descifrado;
    // End of variables declaration//GEN-END:variables
}
