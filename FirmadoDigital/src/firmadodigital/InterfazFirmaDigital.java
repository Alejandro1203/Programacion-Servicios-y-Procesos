package firmadodigital;

import firmadodigital.Managers.InterfaceManager;
import firmadodigital.Managers.ClavesManager;
import firmadodigital.Managers.FirmaDigitalManager;
import java.io.File;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Alejandro
 */
public class InterfazFirmaDigital extends javax.swing.JFrame {
    
    private static byte[] firma;

    /**
     * Creates new form InterfazFirmaDigital
     */
    public InterfazFirmaDigital() {
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

        btn_file_chooser = new javax.swing.JButton();
        txt_path_file = new javax.swing.JTextField();
        btn_claves = new javax.swing.JButton();
        btn_firmar = new javax.swing.JButton();
        btn_emisor = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_file_chooser.setText("Archivo");
        btn_file_chooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_file_chooserMouseClicked(evt);
            }
        });

        txt_path_file.setEnabled(false);

        btn_claves.setText("Generar Claves");
        btn_claves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clavesMouseClicked(evt);
            }
        });

        btn_firmar.setText("Firmar");
        btn_firmar.setEnabled(false);
        btn_firmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_firmarMouseClicked(evt);
            }
        });

        btn_emisor.setText("Emisor");
        btn_emisor.setEnabled(false);
        btn_emisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_emisorMouseClicked(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label1.setText("Generador de Claves");

        label2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label2.setText("Seleccione un Archivo");

        label3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label3.setText("Firmado y Verificación");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btn_claves))
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_file_chooser)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_path_file, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_firmar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_emisor)))))
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_claves)
                .addGap(22, 22, 22)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_file_chooser)
                    .addComponent(txt_path_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_firmar)
                    .addComponent(btn_emisor))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_file_chooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_file_chooserMouseClicked
        txt_path_file.setText(InterfaceManager.fileChooser());
        
        if(!"".equals(txt_path_file.getText())) {
            btn_firmar.setEnabled(true);
        } else {
            btn_firmar.setEnabled(false);
            btn_emisor.setEnabled(false);
        }
    }//GEN-LAST:event_btn_file_chooserMouseClicked

    private void btn_clavesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clavesMouseClicked
        
        try {
            KeyPair claves = ClavesManager.generarClaves();
            ClavesManager.guardarClaves(claves);
            InterfaceManager.generateMessagePopUp(this, "Claves generadas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_clavesMouseClicked

    private void btn_firmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_firmarMouseClicked
        
        try {
            firma = FirmaDigitalManager.firmaDigital(InterfaceManager.fileChooser(), txt_path_file.getText());
            
            if(firma[0] == 0) {
                InterfaceManager.generateErrorPopUp(this, "Fallo al firmar");
                btn_emisor.setEnabled(false);
            } else {
                InterfaceManager.generateMessagePopUp(this, "Firmado correctamente");
                btn_emisor.setEnabled(true);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(InterfazFirmaDigital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_firmarMouseClicked

    private void btn_emisorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_emisorMouseClicked
        
        try {
            
            if(FirmaDigitalManager.firmaEmisor(InterfaceManager.fileChooser(), txt_path_file.getText(), firma)) {
                InterfaceManager.generateMessagePopUp(this, "Mensaje verificado");
            } else {
                InterfaceManager.generateErrorPopUp(this, "Error de validación");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(InterfazFirmaDigital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_emisorMouseClicked

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
            java.util.logging.Logger.getLogger(InterfazFirmaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazFirmaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazFirmaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazFirmaDigital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazFirmaDigital().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_claves;
    private javax.swing.JButton btn_emisor;
    private javax.swing.JButton btn_file_chooser;
    private javax.swing.JButton btn_firmar;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JTextField txt_path_file;
    // End of variables declaration//GEN-END:variables
}