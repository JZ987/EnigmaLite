import java.util. *;
import java.io.*;

public class Cipher{

    public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static String originalText = "";

    private static String encryptedText = "";

    //METHODS

    //Gets a string of text from a txt file
    //parses config file
     public static String getText(String file){
	try{
	    Scanner in = new Scanner(new File(file));
	    for(int p = 0; in.hasNext(); p++){
		String word = in.next();
		originalText = originalText + " " + word + " ";
	    }
	}catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
	    
	return originalText;
     }

    public static String cipher(String text, int shift){
	String etext = "";

	
	if(shift % 26 == 0){
	    return text;
	}

	for(int p = 0; p < text.length(); p++){
	    if(text.substring(p, p + 1).equals(" ")){
		etext = etext + " ";
	    }
	    //System.out.println(originalText.substring(p, p + 1));
	    for(int i = 0; i < 26 - shift; i++){
		if(text.charAt(p) == (alphabet[i])){
		    etext = etext + alphabet[i + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (alphabet[25])){
		    etext = etext + alphabet[-1 + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (ALPHABET[i])){
		    etext = etext + ALPHABET[i + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (ALPHABET[25])){
		    etext = etext + ALPHABET[-1 + shift];
		    //System.out.println(encryptedText);
		}
	    }
	}

	return etext;
    }

    //Deciphers a method encrypted with teh above method
    //UNFINISHED
    public static String decipher(String text, int shift){
	String etext = "";
	shift = -shift;

	
	if(shift % 26 == 0){
	    return text;
	}

	for(int p = 0; p < text.length(); p++){
	    if(text.substring(p, p + 1).equals(" ")){
		etext = etext + " ";
	    }
	    //System.out.println(originalText.substring(p, p + 1));
	    for(int i = 0; i < 26 + shift; i++){
		if(text.charAt(p) == (alphabet[i])){
		    etext = etext + alphabet[i + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (alphabet[25])){
		    etext = etext + alphabet[-1 + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (ALPHABET[i])){
		    etext = etext + ALPHABET[i + shift];
		    //System.out.println(encryptedText);
		}
		if(text.charAt(p) == (ALPHABET[25])){
		    etext = etext + ALPHABET[-1 + shift];
		    //System.out.println(encryptedText);
		}
	    }
	}

	return etext;
    }

    public static void main(String[]args){
	/*
	getText("cipher.txt");
	System.out.println(originalText);
	System.out.println("==================================================");
	encryptedText = cipher(originalText, 1);
	System.out.println(encryptedText);
	System.out.println("==================================================");
	encryptedText = cipher(originalText, 2);
	System.out.println(encryptedText);
	System.out.println("==================================================");
	encryptedText = cipher(originalText, 3);
	System.out.println(encryptedText);
	*/

	System.out.println(getText(args[0]));
	System.out.println("==================================================");
	encryptedText = cipher(getText(args[0]), Integer.parseInt(args[1]));	
	System.out.println(encryptedText);
	System.out.println("==================================================");
	decipher(encryptedText, Integer.parseInt(args[1]));
	System.out.println(encryptedText);
    }
}
