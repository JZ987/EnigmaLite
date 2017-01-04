import java.util. *;
import java.io.*;

public class Cipher{

    public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static String originalText = "";

    private static String encryptedText = "";

    private static String decryptedText = "";

    private static String file;

    private static int shift;

    private static String selector;

    //METHODS

    //Gets a string of text from a txt file
    //parses config file
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

    //Enciphers that data in the originalText variable
    public static void cipher(){
	String etext = "";
	int switched = 0;
	
	if(shift % 26 == 0){
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
	    
	    if(switched == 0){
		etext = etext + originalText.charAt(p);
	    }
	}
	encryptedText = etext;
    }

    //Alternate cipher method
    /*
    public static String cipher(String text, int shift){
	String etext = "";
	int current = -1;
	if(shift % 26 == 0){
	    return text;
	}

	for(int p = 0; p < text.length(); p++){
	    //System.out.println(originalText.substring(p, p + 1));
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
	return etext;
    }
    */

    
    //Deciphers a method encrypted with the above method
    public static void decipher(){
	String detext = "";
	int current = -1;

	for(int p = 0; p < encryptedText.length(); p++){
	    //System.out.println(originalText.substring(p, p + 1));
	    for(int i = 0; i < 26; i++){
		if(encryptedText.charAt(p) == alphabet[i]){
		    if(i - shift < 0){
			detext += alphabet[26 + (i-shift)];
		    }else{
			detext += alphabet[i - shift];
		    }
		    current++;
		    break;
		    //System.out.println(encryptedText);
		}
		if(encryptedText.charAt(p) == ALPHABET[i]){
		    if(i - shift < 0){
			detext += ALPHABET[26 + (i-shift)];
		    }else{
			detext += ALPHABET[i - shift];
		    }
		    current++;
		    break;
		    //System.out.println(encryptedText);
		}
	    }
	    if(current != p){
		detext = detext + encryptedText.charAt(p);
		current++;
	    }
	}

	decryptedText = detext;
    }

    //MAIN
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

    /*
    try{
	PrintWriter writer = new PrintWriter("Encrypted" + file, "UTF-8");
	writer.println(encryptedText);
	writer.close();
    } catch (IOException e) {
	System.exit(1);	    
    }
    */
}
