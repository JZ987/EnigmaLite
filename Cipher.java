import java.util. *;
import java.io.*;
import java.util.ArrayList;
import java.lang.*;

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
	int shiftTempDigits = shift;
	int shiftTempSymbols = shift;
	char tempa = ' ';
	int countera = 0;
	char tempA = ' ';
	int counterA = 0;
	char tempd = ' ';
	int counterd = 0;
	char temps = ' ';
	int counters = 0;
	
	//skips the whole method if shifts would do nothing
	if((shift % 26) == 0){
	    modifiedText = originalText;
	    return;
	}
	
	//ensures shift is a valid input for alphabet and Alphabet
	if((shift % 26) < 0){
	    shift = (26 + (shift % 26));
	}
	else{
	    shift = (shift % 26);
	}
	//ensures shift works for digitswap
	if((shiftTempDigits % 10) < 0){
	    shiftTempDigits = (10 + (shiftTempDigits % 10));
	}
	else{
	    shiftTempDigits = (shiftTempDigits % 10);
	}
	//ensures shift works for symbolswap
	if((shiftTempSymbols % 30) < 0){
	    shiftTempSymbols = (30 + (shiftTempSymbols % 30));
	}
	else{
	    shiftTempSymbols = (shiftTempSymbols % 30);
	}

	/*
	//for testing purposes
	System.out.println("shift: " + shift);
	System.out.println("shiftTempDigits: " + shiftTempDigits);
	System.out.println("shiftTempSymbols: " + shiftTempSymbols);
	*/
	
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
		    tempa = alphabet[(i + shift) % 26];

		    for(int x = 0; x < skips.size(); x++){
			
			if(tempa == skips.get(x)){
			    countera = countera + 1;
			    tempa = alphabet[(i + shift + countera) % 26];
			}
		    }
		    
		    etext = etext + tempa;
		    switched = 26;
		}
	    }
	    
	    //uppercase letters
	    for(int i = 0; (i + switched) < 26; i++){

		if(originalText.charAt(p) == (ALPHABET[i])){
		    tempA = ALPHABET[(i + shift) % 26];

		    for(int x = 0; x < skips.size(); x++){
			
			if(tempA == skips.get(x)){
			    counterA = counterA + 1;
			    tempA = ALPHABET[(i + shift + counterA) % 26];
			}
		    }
		    
		    etext = etext + tempA;
		    switched = 26;
		}
	    }

	    //digits
	    if(swapDigits && (switched != 26)){
	        for(int i = 0; i < 10; i++){
		    if(originalText.charAt(p) == (digit[i])){
			tempd = digit[(i + shift) % 10];

			for(int x = 0; x < skips.size(); x++){
			
			    if(tempd == skips.get(x)){
				counterd = counterd + 1;
				tempd = digit[(i + shift + counterd) % 10];
			    }
			}
		    
			etext = etext + tempd;
			switched = 26;
		    }
		}
	    }

	    //symbols
	    if(swapSymbols && (switched != 26)){
	        for(int i = 0; i < 30; i++){
		    if(originalText.charAt(p) == (symbol[i])){
			temps = symbol[(i + shift) % 30];

			for(int x = 0; x < skips.size(); x++){
			
			    if(temps == skips.get(x)){
				counters = counters + 1;
				temps = symbol[(i + shift + counters) % 30];
			    }
			}
		    
			etext = etext + temps;
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
	int shiftTempDigits = shift;
	int shiftTempSymbols = shift;
	char tempa = ' ';
	int countera = 0;
	char tempA = ' ';
	int counterA = 0;
	char tempd = ' ';
	int counterd = 0;
	char temps = ' ';
	int counters = 0;
	
	//skips the whole method if shifts would do nothing
	if((shift % 26) == 0){
	    modifiedText = originalText;
	    return;
	}
	
	//ensures shift is a valid input for alphabet and ALPHABET
	if((shift % 26) < 0){
	    shift = (26 - (26 + (shift % 26)));
	}
	else{
	    shift = (26 - (shift % 26));
	}
	//ensures shift works for digitswap
	if((shiftTempDigits % 10) < 0){
	    shiftTempDigits = (10 - (10 + (shiftTempDigits % 10)));
	}
	else{
	    shiftTempDigits = (10 - (shiftTempDigits % 10));
	}
	//ensures shift works for symbolswap
	if((shiftTempSymbols % 30) < 0){
	    shiftTempSymbols = (30 - (30 + (shiftTempSymbols % 30)));
	}
	else{
	    shiftTempSymbols = (30 - (shiftTempSymbols % 30));
	}

	/*
	//for testing purposes
	System.out.println("shift: " + shift);
	System.out.println("shiftTempDigits: " + shiftTempDigits);
	System.out.println("shiftTempSymbols: " + shiftTempSymbols);
	*/
	
	//cycles through every element of originalText
	for(int p = 0; p < originalText.length(); p++){

	    //resets the switched checker
	    switched = 0;

	    //checks if an element should be skipped over
	    if(skips.contains(originalText.charAt(p))){
		detext = detext + originalText.charAt(p);
		switched = 26;
	    }
	    
	    //Returns spaces as spaces
	    //currently uneeded
	    //if(originalText.substring(p, p + 1).equals(" ")){
	    //	etext += " ";
	    //}

	    //Cycles through the alphabet arrays replacing each letter in the
	    //text file
	    //lowercase letters
	    for(int i = 0; (i + switched) < 26; i++){
	    	    
		if(originalText.charAt(p) == (alphabet[i])){
		    tempa = alphabet[(i + shift) % 26];

		    for(int x = 0; x < skips.size(); x++){
			
			if(tempa == skips.get(x)){
			    countera = countera + 1;
			    tempa = alphabet[(i + shift + countera) % 26];
			}
		    }
		    
		    detext = detext + tempa;
		    switched = 26;
		}
	    }
	    
	    //uppercase letters
	    for(int i = 0; (i + switched) < 26; i++){

		if(originalText.charAt(p) == (ALPHABET[i])){
		    tempA = ALPHABET[(i + shift) % 26];

		    for(int x = 0; x < skips.size(); x++){
			
			if(tempA == skips.get(x)){
			    counterA = counterA + 1;
			    tempA = ALPHABET[(i + shift + counterA) % 26];
			}
		    }
		    
		    detext = detext + tempA;
		    switched = 26;
		}
	    }

	    //digits
	    if(swapDigits && (switched != 26)){
	        for(int i = 0; i < 10; i++){
		    if(originalText.charAt(p) == (digit[i])){
			tempd = digit[(i + shift) % 10];

			for(int x = 0; x < skips.size(); x++){
			
			    if(tempd == skips.get(x)){
				counterd = counterd + 1;
				tempd = digit[(i + shift + counterd) % 10];
			    }
			}
		    
			detext = detext + tempd;
			switched = 26;
		    }
		}
	    }

	    //symbols
	    if(swapSymbols && (switched != 26)){
	        for(int i = 0; i < 30; i++){
		    if(originalText.charAt(p) == (symbol[i])){
			temps = symbol[(i + shift) % 30];

			for(int x = 0; x < skips.size(); x++){
			
			    if(temps == skips.get(x)){
				counters = counters + 1;
				temps = symbol[(i + shift + counters) % 30];
			    }
			}
		    
			detext = detext + temps;
			switched = 26;
		    }
		}
	    }

	    //adds newlines back into text
	    if(originalText.substring(p, p + 1) == System.lineSeparator()){
		detext = detext + System.lineSeparator();
	    }

	    //Catches unswitched chars
	    if(switched == 0){
		detext += originalText.charAt(p);
	    }

	}//ends the wrapping for loop

	modifiedText = detext;
    }
    

    //autodecrypt method
    //tried to automatically decrypt a file without knowing the input
    public static void autodecrypt(){
	String key = "";

	for(int p = 0; p < originalText.length(); p++){
	    
	}
    }

    
    //ENDS CLASS    
}
