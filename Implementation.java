import java.util.*;
import java.io.*;

public class Implementation{


    private static String selector, originalText, modifiedText, algoType;
    private static boolean swapDigits, swapSymbols;
    private static int shift;
    private static Scanner sc;
    private static ArrayList<Character> skips;

    //debug mode for cipher
    //UNFINISHED
    /*
    public static void cipherDebug(){
	System.out.println("\nPrint file selector swapdigit swapsymbol shift skips");
	String nfile = args[0];
	selector = args[1];
	getText(nfile);
	swapDigits = Boolean.parseBoolean(args[3]);
	swapSymbols = Boolean.parseBoolean(args[4]);
	shift = Integer.parseInt(args[5]);
	for(int i = 6; i < args.length; i++){
	    skips.add(Character.parseChar(args[i]));
	}
    }
    */

    //Begins running the terminal interface
    public static void terminal(){
	originalText = "";
	modifiedText = "";
	sc = new Scanner(System.in);
	chooseWhatToDo();
    }

    //gets text from a specified file
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

    //creates the file and runs the selected methods from cipher to create
    //the encoded/decoded text
    public static void createFile(String fileName){
	Cipher text = new Cipher(originalText, modifiedText, shift, swapDigits, swapSymbols, skips);
	if(selector.equals("encrypt")){
	    text.cipher();
	}else if(selector.equals("decrypt")){
	    text.decipher();
	}else{
	    System.out.println("Error, restart from chooseWhatToDo");
	    chooseWhatToDo();
	}
	//Writes the file to the EnigmaLite folder
	try{
	    PrintWriter writer = new PrintWriter("EnigmaLitebin/" + fileName + ".txt", "UTF-8");
	    writer.println(text.getModifiedText());
	    writer.close();
	    System.out.println(text.getModifiedText());
	} catch (IOException e) {
	    System.out.println("Unexpected Error: Invalid Input");
	    System.exit(1);
	}	    
    }

    //choose to do encryption or decryption
    public static void chooseWhatToDo(){
	System.out.println("\nDo you want to <Encrypt> or <Decrypt> your file?");
	String input = sc.nextLine();
	if(input.equals("Encrypt") || input.equals("encrypt") || input.equals("e")){
	    selector = "encrypt";
	    chooseEncryption();
	}else if(input.equals("Decrypt") || input.equals("decrypt") || input.equals("d")){
	    selector = "decrypt";
	    chooseDecryption();
	    /*
	}else if(input.equals("debug")){
	    cipherDebug();
	    */
	}else{
	    System.out.println("Unknown command");
	    chooseWhatToDo();
	}
    }

    //input the text file
    public static void chooseFile(){
	if(selector.equals("encrypt")){
	    System.out.println("\nInput the location of the text file you want to be encrypted:");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nInput the location of the text file you want to be decrypted:");
	}
	getText(sc.nextLine());
    }

    //choose which type of encryption do you want to use
    public static void chooseEncryption(){
	System.out.println("\nWhich encryption method do you want to use? [Cipher | Symmetric]");
	String input = sc.nextLine();
	if(input.equals("Cipher") || input.equals("cipher") || input.equals("c")){
	    chooseFile();
	    optionCipher();
	}else if(input.equals("Symmetric") || input.equals("symmetric") || input.equals("s")){
	    chooseFile();
	    optionSymmetric();
	}else{
	    System.out.println("<Encryption not known!>");
	    chooseEncryption();
	}
    }

    //choose which type of decryption do you want to use
    public static void chooseDecryption(){
	System.out.println("\nWhich decryption method do you want to use? [Cipher | Symmetric]");
	String input = sc.nextLine();
	if(input.equals("Cipher") || input.equals("cipher") || input.equals("c")){
	    chooseFile();
	    optionCipher();
	}else if(input.equals("Symmetric") || input.equals("symmetric") || input.equals("s")){
	    chooseFile();
	    optionSymmetric();
	}else{
	    System.out.println("<Encryption not known!>");
	    chooseDecryption();
	}
    }

    
    //choose the name of the finishing encrypted text file name
    public static void chooseEncryptedFileName(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWhat would you like the encrypted file name to be? (You don't need to put .txt)");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nWhat would you like the decrypted file name to be? (You don't need to put .txt)");
	}

	//concluding message
	String input = sc.nextLine();
	if(input.equals("")){
	    System.out.println("Please choose a filename");
	    chooseEncryptedFileName();
	}else{
	System.out.println("\nGreat Job!!! Your coded file was successfully created and placed in the folder EnigmaLitebin.");
	createFile(input);
	}
    }

    //Cipher Encryption
    public static void optionCipher(){
	chooseSwapDigit();
	chooseSwapSymbols();
	chooseShift();
	skips = new ArrayList<Character>();
	if(chooseSkipping()){
	    chooseSkipSymbol();
	}
	chooseEncryptedFileName();
    }

    //choose whether you want to shift numbers
    public static void chooseSwapDigit(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWould you like to shift number symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you shift number symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || input.equals("y")){
	    swapDigits = true;
	}else if(input.equals("no") || input.equals("n")){
	    swapDigits = false;
	}else{
	    System.out.println("Please type either <yes> or <no>");
	    chooseSwapDigit();
	}
    }

    //choose whether you want to shift non-alphanumeric symbols
    public static void chooseSwapSymbols(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWould you like to shift non-alphanumeric symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you shift non-alphanumeric symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || (input.equals("y"))){
	    swapSymbols = true;
	}else if(input.equals("no") || input.equals("n")){
	    swapSymbols = false;
	}else{
	    System.out.println("Please type either <yes> or <no>");
	    chooseSwapSymbols();
	}
    }

    //choose how many shifts you want
    public static void chooseShift(){
	if(selector.equals("encrypt")){
	    System.out.println("\nHow many shifts do you want?");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nBy how much did you shift your symbols?");
	}
	try{
	    shift = Integer.parseInt(sc.nextLine());
	    if(shift < 0){
		shift = shift + 26;
	    }
	}catch(NumberFormatException e){
	    System.out.println("Please type numbers!");
	    chooseShift();
	}
    }

    //choose whether you want to skip certain symbols
    public static boolean chooseSkipping(){
	if(selector.equals("encrypt")){
	    System.out.println("\nDo you want to skip certain symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you skip certain symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || (input.equals("y"))){
	    return true;
	}else if(input.equals("no") || input.equals("n")){
	    return false;
	}else{
	    System.out.println("Please pick either <yes> or <no>");
	    return chooseSkipping();
	}
    }

    //if chooseSkipping is true
    public static void chooseSkipSymbol(){
	if(selector.equals("encrypt")){
	    System.out.println("\nInput the letters, symbols, and/or numbers you want the cipher to skip:");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nInput the letters, symbols, and/or numbers you have skipped");
	}
	System.out.println("No spacing is required");
	String input = sc.nextLine();
	for (char c : input.toCharArray()) {
	    skips.add(c);
	}
    }


    //used for selecting symmetric encryption algorithm
    public static void optionSymmetric(){
	System.out.println("\nWhich algorithm do you want to use?");
	System.out.println("1)AES 2)ARCFOUR 3)Blowfish 4)DES 5)DESede 6)HmacMD5 7)RC2 [input the number]");
	String input = sc.nextLine();
	if(input.equals("1")){
	    algoType = "AES";
	}else if(input.equals("2")){
	    algoType = "ARCFOUR";
	}else if(input.equals("3")){
	    algoType = "Blowfish";
	}else if(input.equals("4")){
	    algoType = "DES";
	}else if(input.equals("5")){
	    algoType = "DESede";
	}else if(input.equals("6")){
	    algoType = "HmacMD5";
	}else if(input.equals("7")){
	    algoType = "RC2";
	}else{
	    System.out.println("Please input a number corresponding with the algorithm you want to use");
	    optionSymmetric();
	}
    }

    //END
}
