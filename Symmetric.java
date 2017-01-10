import java.util.*;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;

public class Symmetric{

    private static String algoType, originalText, modifiedText;
    private static SecretKey key;
    private static byte[] unModKey;

    
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
	    KeyGenerator keygenerator = KeyGenerator.getInstance(algoType);
	    SecretKey myDesKey = keygenerator.generateKey();
	    key = myDesKey;
	    
	    System.out.println(myDesKey);
	    
	    Cipher desCipher;
	    desCipher = Cipher.getInstance(algoType);

	    byte[] text = originalText.getBytes("UTF8");

	    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
	    byte[] textEncrypted = desCipher.doFinal(text);

	    String output = new String(textEncrypted);

	    modifiedText = output;
	}catch(Exception e){
	    System.out.println("You failed");
	}
    }

    public static void decrypt(){
	try{
	    Cipher desCipher;
	    desCipher = Cipher.getInstance(algoType);

	    desCipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] textDecrypted = desCipher.doFinal(originalText.getBytes("UTF8"));

	    String output = new String(textDecrypted);

	    modifiedText = output;
	}catch(Exception e){
	    System.out.println(e);
	}
    }
	    
    public static void main(String[] args) {

	Symmetric s = new Symmetric("Hello World", "", "DES");

	/*
	// create new key
	SecretKey secretKey = KeyGenerator.getInstance(algoType).generateKey();
	// get base64 encoded version of the key
	String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	*/
	
	System.out.println(originalText);
	System.out.println("------------------------------------");
	
	s.encrypt();

	System.out.println("-----------------------------------");

	System.out.println(modifiedText);

	System.out.println("------------------------------------");

	s.decrypt();
	System.out.println(modifiedText);
	
	/* try{
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

	    System.out.println(myDesKey);
	    
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");


            byte[] text = "No body can see me.".getBytes("UTF8");


            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println(s);

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println(s);
        }catch(Exception e)
        {
            System.out.println("Exception");
        }
	*/

	
    }
}
