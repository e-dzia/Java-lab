import encryptor.Encryptor;

import java.security.KeyPair;

public class Main {
    
    public static void main(String[] args) {
        try {
            KeyPair keyPair = Encryptor.getKeyPairFromKeyStore("otherkeystore.jks", "otherkey", "s3cr3t", "s3cr3t");
            Encryptor.encrypt("1.txt", keyPair.getPublic());
            Encryptor.decrypt("1.txt-encrypted.txt", keyPair.getPrivate());
        }
        catch (Exception e){
            System.out.println("Wystapil blad.");
        }
    }
}
