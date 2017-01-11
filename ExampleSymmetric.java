import javax.crypto.*;
import javax.crypto.Cipher;

public class ExampleSymmetric{

    public static void main(String[] args){
	
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
	
    }
    
}
