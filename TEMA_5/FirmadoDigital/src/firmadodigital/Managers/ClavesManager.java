package firmadodigital.Managers;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Alejandro
 */
public class ClavesManager {
    
    private static int cont = 0;
 
    public static KeyPair generarClaves() throws NoSuchAlgorithmException {

        KeyPairGenerator generador = KeyPairGenerator.getInstance("DSA");
        generador.initialize(512);   // Admite 512, 768 ó 1024
        KeyPair claves = generador.generateKeyPair();
        cont++;

        return claves;
    }

    public static void guardarClaves(KeyPair claves) throws Exception {
        
        FileOutputStream fos = new FileOutputStream("clave_publica" + cont + ".key");
        fos.write(claves.getPublic().getEncoded());
        fos.close();
        fos = new FileOutputStream("clave_privada" + cont + ".key");
        fos.write(claves.getPrivate().getEncoded());
        fos.close();
    }
    
    public static PublicKey getClavePublica(String rutaClavePublica) throws Exception {

        File ficheroClavePublica = new File(rutaClavePublica);
        byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
        PublicKey clavePublica = keyFactory.generatePublic(publicKeySpec);

        return clavePublica;
    }

    public static PrivateKey getClavePrivada(String rutaClavePrivada) throws Exception {

        File ficheroClavePrivada = new File(rutaClavePrivada);
        byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
        PrivateKey clavePrivada = keyFactory.generatePrivate(privateKeySpec);

        return clavePrivada;
    }
}