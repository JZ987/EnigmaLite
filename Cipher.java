import java.util. *;
import java.io.*;

public class Cipher{

    public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static String originalText, decryptedText, encryptedText;

    private static String file;

    private static int shift;

    private static String selector;

    public Cipher(String originalText, String encryptedText, String decryptedText, int shift){
	this.originalText = originalText;
	this.encryptedText = encryptedText;
	this.decryptedText = decryptedText;
	this.shift = shift;
    }
    
    //METHODS

    //Enciphers that data in the originalText variable
    /*public static void cipher(){
	String etext = "";
	int switched = 0;
	
	if(shift == 0){
	    encryptedText = originalText;
	    return;
	}

	for(int p = 0; p < originalText.length(); p++){
	    //Returns spaces as spaces
	    if(originalText.substring(p, p + 1).equals(" ")){
		etext = etext + " ";
	    }

	    //resets the switched checker
	    switched = 0;
	    	    
	    //Cycles through the alphabet arrays replacing each letter in the
	    //text file
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (alphabet[i])){
		    etext = etext + alphabet[(i + shift) % 26];
		    switched = 26;
		}
	    }

	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (ALPHABET[i])){
		    etext = etext + ALPHABET[(i + shift) % 26];
		    switched = 26;
			}
	    }

	    //returns punctuation
	    if(switched == 0){
		etext = etext + originalText.charAt(p);
	    }
	}
	encryptedText = etext;
	}*/


    //Alternate cipher method

    public static void cipher(){
	String etext = "";
	String text = originalText;
	int current = -1;
	if(shift % 26 == 0){
	    encryptedText = text;
	    return;
	}

	for(int p = 0; p < text.length(); p++){
	    //System.out.println(text.substring(p, p + 1));
	    for(int i = 0; i < 26; i++){
		if(text.charAt(p) == alphabet[i]){
		    etext += alphabet[(i+shift)%26];
		    current++;
		    break;
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == ALPHABET[i]){
		    etext += ALPHABET[(i+shift)%26];
		    current++;
		    break;
		    //System.out.println(encryptedText);
		}
	    }
	    if(current != p){
		etext += text.charAt(p);
		current++;
	    }
	}
	encryptedText =  etext;
    }

    /*
    //Deciphers a method encrypted with the above method, reads an already
    //encrypted originalText
    public static void decipher(){
	String detext = "";
	int switched = 0;

	int temp;
	temp = shift;
	shift = 26 - temp;
	
	for(int p = 0; p < originalText.length(); p++){
	    //Returns spaces as spaces
	    if(originalText.substring(p, p + 1).equals(" ")){
		detext = detext + " ";
	    }

	    //resets the switched checker
	    switched = 0;
	    	    
	    //Cycles through the alphabet arrays replacing each letter in the
	    //text file
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (alphabet[i])){
		    detext = detext + alphabet[(i + shift) % 26];
		    switched = 26;
		}
	    }

	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (ALPHABET[i])){
		    detext = detext + ALPHABET[(i + shift) % 26];
		    switched = 26;
			}
	    }

	    //returns punctuation
	    if(switched == 0){
		detext = detext + originalText.charAt(p);
	    }
	}
        
	decryptedText = originalText;
    }
    */
    
    public static String getOriginalText(){
	return originalText;
    }

    public static String getEncryptedText(){
	return encryptedText;
    }

    public static String getDecryptedText(){
	return decryptedText;
    }
    
    /* //MAIN
    public static void main(String[]args){
	if((args.length != 2) && (args.length != 3)){
	    throw new IllegalArgumentException("Missing or Incomplete Input");
	}

	if(Integer.parseInt(args[1]) < 0){
	    shift = (Integer.parseInt(args[1]) % 26) + 26;
	}
	else{
	    shift = Integer.parseInt(args[1]) % 26;
	}

	file = args[0];

	selector = args[2];
	
	//getText();

	if(selector.equals("cipher")){

	    cipher();
	}

	if(selector.equals("decipher")){
	    decipher();
	}

	System.out.println("Original Text: ");
	System.out.println(originalText);
	System.out.println("==================================================");
	System.out.println("Encrypted Text: ");
	System.out.println(encryptedText);
	System.out.println("==================================================");
	System.out.println("Decrypted Text: ");
	System.out.println(decryptedText);
    }
    */
   
}
