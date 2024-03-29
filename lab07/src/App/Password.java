package App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    static String encrypt(String string) throws NoSuchAlgorithmException {
        return sha1(string);
    }

    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        String password = "123";
        try {
            System.out.println(sha1(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
