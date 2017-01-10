/*import java.util. *;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


//Uses a single key to encrypt and decrypt data
public class Symmetric{

    public static void main(String[] args){

	try{
	    KeyGenerator keygen = KeyGenerator.getInstance("DES");
	    SecretKey myKey = keygenerator.generateKey();

	    Cipher desCipher;
	    desCipher = Cipher.getInstance("DES");

	    byte[] text = "No body can see me.".getBytes("UTF8");
	}
    

	}*/


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Symmetric{

    private static String algoType, originalText, modifiedText;
    private static SecretKey key;

    
    public Symmetric(String originalText, String modifiedText, String algoType){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.algoType = algoType;
	
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

	    encryptedText = output;
	}catch(Exception e){
	    System.out.println("You failed");
	}
    }

    public static void decrypt(){
	try{
	    Cipher desCipher;
	    desCipher = Cipher.getInstance(algoType);

	    desCipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] textDecrypted = desCipher.doFinal(encryptedText.getBytes("UTF8"));

	    String output = new String(textDecrypted);

	    decryptedText = output;
	}catch(Exception e){
	    System.out.println(e);
	}
    }
	    
    public static void main(String[] args) {

	Symmetric s = new Symmetric("Hello World", "", "", "DES");

	// create new key
	SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
	// get base64 encoded version of the key
	String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

	
	System.out.println(encryptedText);
	System.out.println("------------------------------------");
	
	s.encrypt();

	System.out.println(encryptedText);

	System.out.println("------------------------------------");

	s.decrypt();
	System.out.println(decryptedText);
	
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
