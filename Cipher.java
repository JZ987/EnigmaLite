import java.util. *;
import java.io.*;

public class Cipher{

    public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static char[] symbol = {'~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '{', ",", '[', '}', ']', '|', ';', ':' , '<', '.', '>', '/', '?', '', ''}; //missing \ ' "
    
    public static ArrayList skips;

    private static String originalText = "";

    private static String encryptedText = "";

    private static String decryptedText = "";

    private static String file;

    private static int shift;

    private static String selector;

    private static String skip;
    //METHODS

    //Gets a string of text from a txt file
    //parses config file
    //Move to abstract class
     public static void getText(){
	try{
	    Scanner in = new Scanner(new File(file));
	    for(int p = 0; in.hasNext(); p++){
		String word = in.next();
		originalText += word + " ";
	    }
	}catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
     }

     /*
    try{
	PrintWriter writer = new PrintWriter("Encrypted" + file, "UTF-8");
	writer.println(encryptedText);
	writer.close();
    } catch (IOException e) {
	System.exit(1);	    
    }
    */

    //Enciphers that data in the originalText variable
    public static void cipher(){
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
    }

    
    //Deciphers a method encrypted with the above method, reads an already
    //encrypted originalText.
    //Assumes the encrypted text is encrypted using the shift and skips
    //recieved as parameters.
    public static void decipher(){
	String detext = "";
	int switched = 0;

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
		    detext = detext + alphabet[(i + (26 - shift)) % 26];
		    switched = 26;
		}
	    }

	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (ALPHABET[i])){
		    detext = detext + ALPHABET[(i + (26 - shift)) % 26];
		    switched = 26;
			}
	    }

	    //returns punctuation
	    if(switched == 0){
		detext = detext + originalText.charAt(p);
	    }
	}
        
	decryptedText = detext;
    }

    //MAIN
    public static void main(String[]args){
	if((args.length != 2) && (args.length != 29)){
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

	for(int i = 0; i < args.length - 2; i ++){
	    skips.add(args[3 + i];
	}
	
	getText();

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

   
}
