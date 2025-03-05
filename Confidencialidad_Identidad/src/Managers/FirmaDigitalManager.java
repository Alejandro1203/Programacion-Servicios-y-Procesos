package Managers;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class FirmaDigitalManager {
    
    public static byte[] firmaDigital(String path, String pathFile) {
        byte[] firma = new byte[256];
    
        try {

            if (!"".equals(path)) {
                Signature signature = Signature.getInstance("DSA");
                signature.initSign(ClavesManager.getClavePrivada(path));
                signature.update(InterfaceManager.readFile(pathFile).getBytes());
                firma = signature.sign();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return firma;
    }  
    
    public static boolean firmaEmisor(String pathAbsolut, String pathFile, byte[] firma) {
        boolean firmado = false;
        
        try {
            if (!"".equals(pathAbsolut)) {
                Signature signature = Signature.getInstance("DSA");
                signature.initVerify(ClavesManager.getClavePublica(pathAbsolut));
                signature.update(InterfaceManager.readFile(pathFile).getBytes());

                if (signature.verify(firma)) {
                    firmado = true;
                } else {
                    firmado = false;
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return firmado;
    }
}