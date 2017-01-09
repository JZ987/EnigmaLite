import java.util.*;
import java.io.*;

public class Implementation{

    private static String originalText, encryptedText, decryptedText;
    private static int shift;
    private static Scanner sc;
    
    public Implementation(){
        encryptedText = "";
	decryptedText = "";
	originalText = "";
    }

    public static void terminal(){
	sc = new Scanner(System.in);
	
	chooseFile();
	System.out.println("\n" + originalText);
	chooseEncryption();
    }

    
    public static void getText(String file){
	try{
	    Scanner in = new Scanner(new File(file));
	    for(int p = 0; in.hasNext(); p++){
		String word = in.next();
		originalText += word + " ";
	    }
	}catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path!");
	    chooseFile();
	}
    }

    public static void createEncryptedFile(String fileName){
	
	Cipher text = new Cipher(originalText, encryptedText, decryptedText, shift);
	text.cipher();

	try{
	    PrintWriter writer = new PrintWriter(fileName + ".txt", "UTF-8");
	    writer.println(text.getEncryptedText());
	    writer.close();
	} catch (IOException e) {
	    System.exit(1);
	}
	    
    }

    public static void chooseFile(){
	System.out.println("\nInput the text file you want to be encrpted:");
	getText(sc.next());
    }

    public static void chooseEncryption(){
	System.out.println("\nWhich encryption do you want to use? [Cipher | Symmetric]");
	String input = sc.next();
	if(input.equals("Cipher") || input.equals("cipher")){
	    optionCipher();
	}else if(input.equals("Symmetric") || input.equals("symmetric")){
	    optionSymmetric();
	}else{
	    System.out.println("<Encryption not known>");
	    chooseEncryption();
	}
    }

    public static void chooseEncryptedFileName(){
	System.out.println("\nWhat would you like the encrypted file name to be? (You don't need to put .txt)");
	String input = sc.next();
	if(input.equals("")){
	    System.out.println("You failed");
	    createEncryptedFile("Encrypted");
	}else{
	    System.out.println("Yay success");
	    createEncryptedFile(input);
	}
    }
    
    public static void optionCipher(){
	System.out.println("\nDo you want to <shift> or <skip>?");
	String input = sc.next();

	if(input.equals("shift")){
	    optionShift();
	}else if(input.equals("skip")){
	    optionSkip();
	}else{
	    System.out.println("Unknown option!");
	    optionCipher();
	}	       
    }

    public static void optionShift(){
	System.out.println("\nHow many shifts?");
	try{
	    shift = Integer.parseInt(sc.next());
	}catch(NumberFormatException e){
	    System.out.println("Please type numbers!");
	    optionShift();
	}
	chooseEncryptedFileName();
    }

    public static void optionSkip(){

    }

    public static void optionSymmetric(){

    }
    
    public static String getOriginalText(){
	return originalText;
    }

    public static String getEncryptedText(){
	return encryptedText;
    }

    public static String getDecryptedText(){
	return decryptedText;
    }

}
