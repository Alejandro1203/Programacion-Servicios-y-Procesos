package almacenamientoseguro;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Alejandro
 */
public class AES_Manager {
    
    public static Key obtenerClave(String password, int longitud) {
        Key clave = new SecretKeySpec(password.getBytes(), 0, longitud, "AES");
        return clave;
    }
    
    public static String cifrar(String textoEnClaro, Key clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] cipherText = cipher.doFinal(textoEnClaro.getBytes());
        
        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    public static String descifrar(String textoCifrado, Key clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
        
        return new String(plainText);
    }
}