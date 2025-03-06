package Managers;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.Cipher;

/**
 *
 * @author Alejandro
 */
public class RSAManager {
    
    public static byte[] cifrar(String mensaje, Key clave) throws Exception{
        
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] mensajeBytes = mensaje.getBytes(StandardCharsets.UTF_8);
        byte[] mensajeCifradoBytes = encryptCipher.doFinal(mensajeBytes);
        
        return mensajeCifradoBytes;
    }
    
    public static byte[] descifrar(byte[] mensajeCifrado, Key clave) throws Exception {
        
        Cipher descifrador = Cipher.getInstance("RSA");
        descifrador.init(Cipher.DECRYPT_MODE, clave);
        byte[] mensajeDescifrado = descifrador.doFinal(mensajeCifrado);
        
        return mensajeDescifrado;
    }
}