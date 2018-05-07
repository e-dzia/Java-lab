package encryptor;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;


public class Encryptor {
    
    public static void main(String[] args) {
        try {
            KeyPair keyPair = getKeyPairFromKeyStore("keystore.jks", "mykey", "secret", "secret");
            encrypt("1.txt", keyPair.getPublic());
            decrypt("1.txt-encrypted.txt", keyPair.getPrivate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean encrypt(String pathToFile, PublicKey publicKey) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(pathToFile));
        String plainText = new String(encoded, "UTF8");
        
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));
    
        try (FileOutputStream fos = new FileOutputStream(pathToFile + "-encrypted.txt")) {
            fos.write(cipherText);
        }
        return true;
    }
    
    public static boolean decrypt(String pathToFile, PrivateKey privateKey) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(pathToFile));
        
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    
        try (FileOutputStream fos = new FileOutputStream(pathToFile + "-decrypted.txt")) {
            fos.write(decriptCipher.doFinal(bytes));
        }
        return true;
    }
    
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
        
        return pair;
    }
    
    public static KeyPair getKeyPairFromKeyStore(String keyFilename, String keyAlias, String keystorePass, String keyPass) throws Exception {
        InputStream ins = new FileInputStream(keyFilename);
        
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(ins, keystorePass.toCharArray());   //Keystore password
        KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(keyPass.toCharArray()); //Key password
        
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, keyPassword);
        
        java.security.cert.Certificate cert = keyStore.getCertificate(keyAlias);
        PublicKey publicKey = cert.getPublicKey();
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        
        return new KeyPair(publicKey, privateKey);
    }
}
