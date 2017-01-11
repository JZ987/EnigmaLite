import java.util.Base64;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.spec.*;
import java.util.Scanner;

public class Symmetric{

    private static String algoType, originalText, modifiedText, exampleText, stringKey;
    private static SecretKey key1, key2;
    private static byte[] unModKey, textEncrypted, textDecrypted;
    private static Cipher desCipher;

    
    public Symmetric(String originalText, String modifiedText, String algoType, String stringKey){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.algoType = algoType;
	this.stringKey = stringKey;
    }

    //Convert a String to SecretKey
    public static void convertToKey(){
	byte[] decodedKey = Base64.getDecoder().decode(stringKey);
	SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algoType);
	key1 = originalKey;
    }
    
    public static void convertToKey(String key){
	try{
	    byte[] decodedKey = Base64.getDecoder().decode(key);
	    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algoType);
	    key2 = originalKey;
	}catch(Exception e){
	    System.out.println("Bad key!!");
	}
    }

    
    //Encrypting a text file with a key
    public static void encrypt(){
	try{
	    byte[] text = originalText.getBytes("UTF8");

	    desCipher.init(Cipher.ENCRYPT_MODE, key1);
	    textEncrypted = desCipher.doFinal(text);

	    String output = new String(textEncrypted);

	    modifiedText = output;
	}catch(Exception e){
	    System.out.println("You failed");
	}
    }
    
    //Decrypting an encrypted text file with the proper key
    public static void decrypt(){
	try{
	    
	    desCipher.init(Cipher.DECRYPT_MODE, key2);
	    textDecrypted = desCipher.doFinal(textEncrypted);

	    String output = new String(textDecrypted);

	    exampleText = output;
	    
	}catch(Exception e){
	    System.out.println("\nWrong password");
	}
    }
	    
    public static void main(String[] args) {


	Symmetric s = new Symmetric("Hello world", "", "DES", "16grand76oh");
	Scanner sc = new Scanner(System.in);
	

	//Symmetric s = new Symmetric("No body can see me.", "", "AES/CBC/PKCS5Padding");

	try{
	    // create new key
	    //SecretKey secretKey = KeyGenerator.getInstance(algoType).generateKey();
	    //key1 = secretKey;
	    // get base64 encoded version of the key
	    //String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	    //stringKey = encodedKey;
	    s.convertToKey();
	    desCipher = Cipher.getInstance(algoType);
	}catch(Exception e){
	    System.out.println("you suck");
	}

	

	System.out.println(key1);
	System.out.println(originalText);
	System.out.println("Password: " + stringKey);
	System.out.println("------------------------------------");
	
	s.encrypt();

	System.out.println(modifiedText);

	System.out.println("------------------------------------");
	System.out.println("\nInput the password:");
	
	s.convertToKey(sc.next());
	
	s.decrypt();
	System.out.println(exampleText);
    }
}

