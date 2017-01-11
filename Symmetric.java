import java.util.*;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;

public class Symmetric{

    private static String algoType, originalText, modifiedText, exampleText;
    private static SecretKey key;
    private static byte[] unModKey;
    private static Cipher desCipher;

    
    public Symmetric(String originalText, String modifiedText, String algoType){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.algoType = algoType;
    }

    //Convert a String to SecretKey
    public static void convertToKey(String key){
	byte[] byteKey = Base64.getDecoder().decode(key);
	unModKey = byteKey;
    }
    
    public static void encrypt(){
	try{
	    byte[] text = originalText.getBytes("UTF8");

	    desCipher.init(Cipher.ENCRYPT_MODE, key);
	    byte[] textEncrypted = desCipher.doFinal(text);

	    String output = new String(textEncrypted);

	    modifiedText = output;
	}catch(Exception e){
	    System.out.println("You failed");
	}
    }
    

    public static void decrypt(){
	try{
	    byte[] text = modifiedText.getBytes("UTF8");
	    
	    desCipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] textDecrypted = desCipher.doFinal(text);

	    String output = new String(textDecrypted);

	    exampleText = output;
	}catch(Exception e){
	    System.out.println(e);
	}
    }
	    
    public static void main(String[] args) {


	Symmetric s = new Symmetric("Hello world", "", "DES");

	

	//Symmetric s = new Symmetric("No body can see me.", "", "AES/CBC/PKCS5Padding");

	try{
	    KeyGenerator keygenerator = KeyGenerator.getInstance(algoType);
	    SecretKey myDesKey = keygenerator.generateKey();
	    key = myDesKey;
	    desCipher = Cipher.getInstance(algoType);
	}catch(Exception e){
	    System.out.println("you suck");
	}

	
	/*
	// create new key
	SecretKey secretKey = KeyGenerator.getInstance(algoType).generateKey();
	// get base64 encoded version of the key
	String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	*/
	

	System.out.println(key);
	System.out.println(originalText);
	System.out.println("------------------------------------");
	
	s.encrypt();

	System.out.println("-----------------------------------");

	System.out.println(modifiedText);

	System.out.println("------------------------------------");

	s.decrypt();
	System.out.println(exampleText);
    }
	
	/* try{
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

	    System.out.println(exampleText);*/


	/*public static void main(String[] args){
	    
	    try{
		KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		SecretKey myDesKey = keygenerator.generateKey();

		System.out.println("---------------------------------");
		System.out.println(myDesKey);
	    
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");

		
		byte[] text = "No body can see me  gsdgs.".getBytes("UTF8");


		desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
		byte[] textEncrypted = desCipher.doFinal(text);

		System.out.println("---------------------------------");
		String s = new String(textEncrypted);
		System.out.println(s);

		System.out.println("---------------------------------");
		desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
		byte[] textDecrypted = desCipher.doFinal(textEncrypted);

		s = new String(textDecrypted);
		System.out.println(s);
	    }catch(Exception e)
		{
		    System.out.println("Exception");
		}
	    
	}*/
}

