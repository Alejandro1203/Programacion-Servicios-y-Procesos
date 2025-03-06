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
    
    public static byte[] firmaDigital(String path, String nameFile) {
        byte[] firma = new byte[256];
    
        try {

            if (!"".equals(path)) {
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initSign(ClavesManager.getClavePrivada(path));
                signature.update(InterfaceManager.readBytesFile(nameFile));
                firma = signature.sign();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FirmaDigitalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return firma;
    } 
    
    public static boolean firmaEmisor(String pathAbsolut, String nameFile, byte[] firma) {
        boolean firmado = false;
        
        try {
            if (!"".equals(pathAbsolut)) {
//                Signature signature = Signature.getInstance("DSA");
                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initVerify(ClavesManager.getClavePublica(pathAbsolut));
                signature.update(InterfaceManager.readBytesFile(nameFile));

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
