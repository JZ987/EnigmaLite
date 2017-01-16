import java.util.Base64;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.*;
import java.util.Scanner;

public class Symmetric{

    private static String originalText, modifiedText, algoType, stringKey;
    private static SecretKey key;
    private static byte[] decodedKey;
    private static Cipher desCipher;

    
    public Symmetric(String originalText, String modifiedText, String algoType){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.algoType = algoType;
	try{
	    desCipher = Cipher.getInstance(algoType);
	}catch(Exception e){
	    System.out.println("Sad");
	}
    }

    
    public static void convertToKey(String keystring){
	try{
	    decodedKey = Base64.getDecoder().decode(keystring);
	    key = new SecretKeySpec(decodedKey, algoType);
	    //key = new SecretKeySpec(decodedKey, 0, decodedKey.length, algoType);
	}catch(Exception e){
	    System.out.println("Bad key!!");
	}
    }


    public static void generateKey(){
	try{
	    // create new key
	    SecretKey secretKey = KeyGenerator.getInstance(algoType).generateKey();
	    key = secretKey;
	    // get base64 encoded version of the key
	    String encodedKey = Base64.getEncoder().withoutPadding().encodeToString(secretKey.getEncoded());
	    stringKey = encodedKey;
	}catch(Exception e){
	    System.out.println("You suck");
	    System.out.println(e);
	}
    }

    
    //Encrypting a text file with a key
    public static void encrypt(){
	generateKey();
	System.out.println("\nYour password will be: " + stringKey);
	try{
	    byte[] text = originalText.getBytes("UTF-8");

	    desCipher.init(Cipher.ENCRYPT_MODE, key);
	    
	    modifiedText = Base64.getEncoder().withoutPadding().encodeToString(desCipher.doFinal(text));
	}catch(Exception e){
	    System.out.println("Encryption failed due to: ");
	    System.out.print(e);
	}
    }


    
    //Decrypting an encrypted text file with the proper key
    public static void decrypt(){
	Scanner scan = new Scanner(System.in);
	System.out.println("\nInput the password: ");
	convertToKey(scan.nextLine());

	try{
	    byte[] encodedText = Base64.getMimeDecoder().decode(originalText);
	    desCipher.init(Cipher.DECRYPT_MODE, key);
	    modifiedText = new String(desCipher.doFinal(encodedText));
	}catch(Exception e){
	    System.out.println("I hate my life");
	    System.out.println("\nDecryption failed due to: ");
	    System.out.println(e);
	    //System.out.print("Wrong password");
	}
    }

    public static String getKey(){
	return stringKey;
    }

    public static String getModifiedText(){
	return modifiedText;
    }
}

