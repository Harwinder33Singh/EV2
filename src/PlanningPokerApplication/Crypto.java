/*
Author: Preetom Kumar Biswas
*/

package PlanningPokerApplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Crypto {
    private String key;

    public Crypto(String key) {
        this.key = key;
    }

    public String encryptString(String input) throws Exception {
    	SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptString(String encryptedInput) throws Exception {
    	SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
        return new String(decryptedBytes);
    }
    

    public static void main(String[] args) throws Exception {
        // Example string to encrypt
        String originalString = "Preetom#123";

        // Key (keep this key secret and safe)
        String key = "YourSecretKey123"; // Change this to your own secret key

        // Create an instance of StringEncryption with the key
        Crypto stringEncryption = new Crypto(key);

        // Encrypt the string
        String encryptedString = stringEncryption.encryptString(originalString);
        System.out.println("Encrypted: " + encryptedString);

        // Decrypt the string (using the same key)
        String decryptedString = stringEncryption.decryptString(encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }

}
