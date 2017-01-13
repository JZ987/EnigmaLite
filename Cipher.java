import java.util. *;
import java.io.*;
import java.util.ArrayList;

public class Cipher/*extends Implementation*/{

    ///*
    //fed from terminal
    private static String originalText = "";

    //fed from terminal
    private static String modifiedText = "";

    //included for testing
    private static String file;

    //fed from terminal
    private static int shift;

    //fed from terminal
    public static ArrayList<Character> skips = new ArrayList<Character>(' ');

    //fed from terminal
    private static String selector;//selects cipher or decipher

    //fed from terminal
    private static Boolean swapDigits;

    //fed from terminal
    private static Boolean swapSymbols;

    //fed from terminal
    private static char assignSpace;//allows the user to assign a symbol to
    //replace 'space'. This symbol is then added to the skip list.
    //*/

    //Reference library arrays
    public static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static char[] symbol = {'{', '`', '!', '[', '#', '$', '%', '^', '&', '*', '_', ')', '-', '(', '=', ',', '~', '+', '"', '@', '}', ';', '|', ']', ':' , '<', '.', '>', '/', '?'}; //unsupported chars: \ '


    //Constructor
    public Cipher(String originalText, String modifiedText, int shift, boolean swapDigits, boolean swapSymbols, ArrayList<Character> skips){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.shift = shift;
	this.swapDigits = swapDigits;
	this.swapSymbols = swapSymbols;
	this.skips = skips;
    }

    //Accessor Methods
    public static String getOriginalText(){
	return originalText;
    }

    public static String getModifiedText(){
	return modifiedText;
    }
    
    //*****METHODS*****
    //Enciphers the data in the originalText variable
    public static void encrypt(){
	String etext = "";
	int switched = 0;
	
	if(shift == 0){
	    modifiedText = originalText;
	    return;
	}

	//cycles through every element of originalText
	for(int p = 0; p < originalText.length(); p++){

	    //resets the switched checker
	    switched = 0;

	    //checks if an element should be skipped over
	    if(skips.contains(originalText.charAt(p))){
		etext = etext + originalText.charAt(p);
		switched = 26;
	    }
	    
	    //Returns spaces as spaces
	    //if(originalText.substring(p, p + 1).equals(" ")){
	    //	etext += " ";
	    //}
	    	    
	    //Cycles through the alphabet arrays replacing each letter in the
	    //text file
	    //lowercase letters
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (alphabet[i])){
		    if(skips.contains(alphabet[(i + shift) % 26])){
			etext = etext + alphabet[(i + shift/**/) % 26];
		    }
		    else{
			etext = etext + alphabet[(i + shift) % 26];
		    }
		    switched = 26;
		}
	    }
	    //uppercase letters
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (ALPHABET[i])){
		    etext = etext + ALPHABET[(i + shift) % 26];
		    switched = 26;
			}
	    }

	    //digits
	    if(swapDigits && (switched != 26)){
		for(int i = 0; i < 10; i++){
		    if(originalText.charAt(p) == (digit[i])){
			etext = etext + digit[(i + shift) % 10];
			switched = 26;
		    }
		}
	    }

	    //symbols
	    if(swapSymbols && (switched != 26)){
		for(int i = 0; i < 30; i++){
		    if( (originalText.charAt(p) == (symbol[i])) && !(skips.contains(symbol[i]))){
			etext = etext + symbol[(i + shift) % 30];
			switched = 26;
		    }
		}
	    }

	    //adds newlines back into text
	    if(originalText.substring(p, p + 1) == System.lineSeparator()){
		etext = etext + System.lineSeparator();
	    }

	    //Catches unswitched chars
	    if(switched == 0){
		etext += originalText.charAt(p);
	    }

	}//ends the wrapping for loop
	modifiedText = etext;
    }

    
    //Deciphers a method encrypted with the above method, reads an already
    //encrypted originalText.
    //Assumes the encrypted text is encrypted using the shift and skips
    //recieved as parameters.
    public static void decrypt(){
	String detext = "";
	int switched = 0;

	for(int p = 0; p < originalText.length(); p++){
	    
	    //resets the switched checker
	    switched = 0;
	    
	    //Returns spaces as spaces
	    if(originalText.substring(p, p + 1).equals(" ")){
		detext += " ";
	    }

	    //checks if an element should be skipped over
	    if(skips.contains(originalText.charAt(p))){
		detext += originalText.charAt(p);
		switched = 26;
	    }
	    
	    //Cycles through the alphabet arrays replacing each letter in the
	    //text file

	    //lowercase
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (alphabet[i])){
		    detext += alphabet[(i + (26 - shift)) % 26];
		    switched = 26;
		}
	    }

	    //uppercase
	    for(int i = 0; (i + switched) < 26; i++){
		if(originalText.charAt(p) == (ALPHABET[i])){
		    detext += ALPHABET[(i + (26 - shift)) % 26];
		    switched = 26;
			}
	    }

	    //digits
	    if(swapDigits && (switched != 26)){
		for(int i = 0; i < 10; i++){
		    if(originalText.charAt(p) == (digit[i])){
			detext += digit[(i + (10 - shift)) % 10];
			switched = 26;
		    }
		}
	    }

	    //symbols
	    if(swapSymbols && (switched != 26)){
		for(int i = 0; i < 30; i++){
		    if(originalText.charAt(p) == (symbol[i])){
			detext += symbol[(i + (30 - shift)) % 30];
			switched = 26;
		    }
		}
	    }

	    //Catches unswitched chars
	    if(switched == 0){
		detext += originalText.charAt(p);
	    }
	    
	}//ends wrapping for loop

	modifiedText = detext;
    }
    //ENDS CLASS
}
